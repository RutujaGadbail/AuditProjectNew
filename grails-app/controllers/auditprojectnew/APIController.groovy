package auditprojectnew

import grails.converters.JSON

import java.awt.Event
import java.sql.Timestamp

class APIController {

   def getEvents() {
       response.setContentType('application/json')
       Calendar calendar = Calendar.getInstance()
       calendar.set(Calendar.DATE, 1)
       calendar.add(Calendar.DATE, -1)
       Timestamp time = new Timestamp(calendar.getTimeInMillis());

       List<Audit> auditCalculation = Audit.findAllByEntryDateGreaterThan(time)

       Map<String,List<Audit>> groupCalculation = auditCalculation.groupBy ({it.entryText})

       Map<String, Integer> totalEvents = new HashMap<>()
       println "groupCalculation: $groupCalculation"

       groupCalculation.keySet().each { type ->
           totalEvents.put(type,groupCalculation[type].size())
       }
       println "count2: $totalEvents"
       render(status: 200, "${[groupCalculation: groupCalculation] as JSON}")
   }}

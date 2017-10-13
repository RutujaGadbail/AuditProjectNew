package auditprojectnew

import grails.converters.JSON
import grails.converters.XML
import grails.rest.RestfulController
import org.fusesource.jansi.AnsiRenderer

import java.awt.Event
import java.sql.Timestamp
import groovy.json.JsonSlurper

class AuditController {
    private final static String SHOW_ALL_AUDIT_EVENT_TYPES = "1"
    private final static String SHOW_USER_SELECTED_EVENT_TYPE_ONLY = "2"

    private final static String SHOW_EVENTS_FOR_ALL_USERS = "1"
    private final static int DEFAULT_NUMBER_OF_EVENTS_TO_FETCH = 10
    private final static String SHOW_EVENTS_FOR_ALL_APPS = "1"

    def auditService
  static scaffold = Audit

    def log() {
        response.setContentType('application/json')
        try {
            println params
            println params.auditEvent
            println params.userId
            println params.userName
            println params.text
            println params.appCode
            String auditToken = auditService.log(params.auditEvent, params.userId, params.userName, params.text, params.appCode)
            render("${[auditToken: auditToken] as JSON}")

        }
        catch (Exception exception) {
            log.error "Error saving audit " + exception.message, exception
            render(status: 500, "${['status': "Error saving audit"] as JSON}")
        }

    }

    def showChart(){
        render(view: "chart")
    }

    /*def getAggregatedEvents(){

    }*/
    def list(){
        List<String> auditEventTypes = auditService.getAuditEventTypes()
        List<String> auditEntryCode = auditService.getAuditEventCode()
        List<String> allUserNames = auditService.getNamesOfAllAuditedUsers()
        Calendar start = Calendar.getInstance()
        start.set(Calendar.HOUR_OF_DAY, 0)
        start.set(Calendar.MINUTE, 0)

        print "auditevent" + auditEventTypes
        print "auditEntryCode" + auditEntryCode
        print "allUserNames" + allUserNames

        boolean initialPageLoad = !params.auditTypesToShow

        if(initialPageLoad) {
            render(view: "showEntries", model: [auditEntries: new ArrayList<Audit>(), auditEventTypes: auditEventTypes, auditEntryCode: auditEntryCode, userNames: allUserNames, todayStart: start])
            return
        }
        println(params.appNames)
        println(params.auditAppToShow)
        boolean showEventsForAllApps = params.appNames && params.appNames == SHOW_EVENTS_FOR_ALL_APPS
//        boolean showUserSelectedEventTypeOnly=params.type && params.type==SHOW_USER_SELECTED_EVENT_TYPE_ONLY
        boolean showEventsForAllUsers = params.users && params.users == SHOW_EVENTS_FOR_ALL_USERS
        int maxEntriesToFetch

        if (params.maxEntriesToShow && ((String) params.maxEntriesToShow).isInteger()) {
            maxEntriesToFetch = Integer.parseInt(params.maxEntriesToShow)
        } else {
            maxEntriesToFetch = DEFAULT_NUMBER_OF_EVENTS_TO_FETCH
        }
        String sortOrder = params.order == "1" ? "asc" : "desc"

        def to = params.toDate
        def from = params.fromDate

        if (to > new Date() && to < from) {
            to = new Date()
        }
        if (from > new Date()) {
            from = new Date()
        }
        Timestamp fromDate = new Timestamp(from.getTime())
        Timestamp toDate = new Timestamp(to.getTime())
        def auditEntries = []
        boolean  showAllEvents = SHOW_ALL_AUDIT_EVENT_TYPES==params.auditTypesToShow
        String eventTypeToShow = params.auditEventToShow
//        if(showUserSelectedEventTypeOnly)
        if(showEventsForAllApps){
            if(showEventsForAllUsers){
                if(showAllEvents){
                    auditEntries = auditService.getAllDateFilteredAuditEntries(fromDate, toDate, sortOrder, maxEntriesToFetch,)
                }else {
                    auditEntries =auditService.getDateAndTypeFilteredAuditEntries(eventTypeToShow, fromDate, toDate, sortOrder, maxEntriesToFetch,)
                }
            }else {
                if(showAllEvents){
                    auditEntries =auditService.getDateFilteredAuditEntriesForUser(params.userName, fromDate, toDate, sortOrder, maxEntriesToFetch,)
                }else {
                    auditEntries = auditService.getDateAndTypeFilteredAuditEntriesForUser(eventTypeToShow, fromDate, toDate, params.userName, sortOrder, maxEntriesToFetch,)
                }  }

        }
//     else{auditEntries=auditService.getDateAndTypeFilteredAuditEntries(eventTypeToShow,fromDate, toDate, sortOrder, maxEntriesToFetch, )}
else {
           String appCode = params.auditAppToShow
            if(showEventsForAllUsers){
                if(showAllEvents){
                    auditEntries = auditService.getAllDateFilteredAuditEntriesForApp(appCode,fromDate, toDate, sortOrder, maxEntriesToFetch,)
                }else {
                   auditEntries =auditService.getDateAndTypeFilteredAuditEntriesForApp(appCode,eventTypeToShow, fromDate, toDate, sortOrder, maxEntriesToFetch,)
                }
            }else {
                if(showAllEvents){
                    auditEntries =auditService.getDateFilteredAuditEntriesForUserForApp(params.userName,appCode,fromDate, toDate, sortOrder, maxEntriesToFetch,)
                }else {
                    auditEntries = auditService.getDateAndTypeFilteredAuditEntriesForUserForApp(appCode,eventTypeToShow, fromDate, toDate, params.userName, sortOrder, maxEntriesToFetch,)
                }
            }
        }
        render(view: "showEntries",
                model: [auditEntries   : auditEntries,
                        auditEventTypes: auditEventTypes,
                        auditEntryCode : auditEntryCode,
                        userNames      : allUserNames,
                        todayStart     : start])
    }


}

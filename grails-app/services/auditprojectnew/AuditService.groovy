package auditprojectnew

import grails.transaction.Transactional

import java.sql.Timestamp

@Transactional
class AuditService {

    def log(String auditEventType, String userId, String userName,  String text, String appCode) {
        Audit audit = new Audit()
        audit.type = auditEventType
        audit.entryText = text
        audit.entryCode = appCode
        audit.userId = userId ? userId : "N/A"
        audit.userName = userName ? userName : "N/A"
        audit.save(failOnError: true)
        "11111"
    }


    @Transactional(readOnly = true)
    List<Audit> getDateAndTypeFilteredAuditEntries(String auditEventType, Timestamp fromDate, Timestamp toDate, String order, int maxEntries) {

        return Audit.findAllByTypeAndEntryDateBetween(auditEventType, fromDate, toDate, [sort: 'entryDate', order: order, max: maxEntries])

    }

    @Transactional(readOnly = true)
    List<Audit> getDateFilteredAuditEntriesForUser(String userName, Timestamp fromDate, Timestamp toDate, String order, int maxEntries) {

        return Audit.findAllByUserNameAndEntryDateBetween(userName, fromDate, toDate, [sort: 'entryDate', order: order, max: maxEntries])
    }

    @Transactional(readOnly = true)
    List<Audit> getDateAndTypeFilteredAuditEntriesForUser(String auditEventType, Timestamp fromDate, Timestamp toDate, String userName, String order, int maxEntries) {

        return Audit.findAllByTypeAndUserNameAndEntryDateBetween(auditEventType, userName, fromDate, toDate, [sort: 'entryDate', order: order, max: maxEntries])

    }
    @Transactional(readOnly = true)
    List<Audit> getAllDateFilteredAuditEntries(Timestamp fromDate, Timestamp toDate, String order, int maxEntries) {

        return Audit.findAllByEntryDateBetween(fromDate, toDate, [sort: 'entryDate', order: order, max: maxEntries])

    }

    @Transactional(readOnly = true)
    List<Audit> getAllDateFilteredAuditEntriesForApp(String app, Timestamp fromDate, Timestamp toDate, String order, int maxEntries) {

        return Audit.findAllByEntryDateBetweenAndEntryCode(fromDate, toDate, app, [sort: 'entryDate', order: order, max: maxEntries])

    }
    @Transactional(readOnly = true)
    List<Audit> getDateAndTypeFilteredAuditEntriesForApp(String app,String auditEventType, Timestamp fromDate, Timestamp toDate, String order, int maxEntries) {

        return Audit.findAllByTypeAndEntryDateBetweenAndEntryCode(auditEventType, fromDate, toDate,app, [sort: 'entryDate', order: order, max: maxEntries])

    }
    @Transactional(readOnly = true)
    List<Audit> getDateFilteredAuditEntriesForUserForApp(String app,String userName, Timestamp fromDate, Timestamp toDate, String order, int maxEntries) {

        return Audit.findAllByUserNameAndEntryDateBetweenAndEntryCode(userName, fromDate, toDate,app, [sort: 'entryDate', order: order, max: maxEntries])
    }
    @Transactional(readOnly = true)
    List<Audit> getDateAndTypeFilteredAuditEntriesForUserForApp(String app,String auditEventType, Timestamp fromDate, Timestamp toDate, String userName, String order, int maxEntries) {

        return Audit.findAllByTypeAndUserNameAndEntryDateBetweenAndEntryCode(auditEventType,userName, fromDate, toDate,app, [sort: 'entryDate', order: order, max: maxEntries])
    }
    /**
     * Returns a list of audit event types
     */
    @Transactional(readOnly = true)
    List<String> getAuditEventTypes() {

        List<String> auditEvents = Audit.withCriteria {
            projections { distinct("type") }
        }
        return auditEvents
    }
    @Transactional(readOnly = true)
    List<String> getAuditEventCode() {

        List<String> auditEventsCode = Audit.withCriteria {
            projections { distinct("entryCode") }
        }
        return auditEventsCode
    }
    /**
     * Returns a list of unique user names that have raised audit events
     */
    @Transactional(readOnly = true)
    List<String> getNamesOfAllAuditedUsers() {

        List<String> userNames = Audit.withCriteria {
            projections { distinct("userName") }
        }
        if (userNames.contains(null)) {
            userNames.remove(Collections.singleton(null))
        }
        return userNames
    }
}

package auditprojectnew

import java.sql.Timestamp

class Audit {

    Timestamp entryDate = new Timestamp(System.currentTimeMillis())
    String type
    String entryText
    // Important, we only store id's here and not reference these domain objects directly in grails, this avoids referential
    // integrity issues when objects are dropped and ensures audit logs do not change once written.
    String  userId
    // We store the names of entities and users incase they get deleted (useful for when just viewing audit logs)
    String userName
     String entryCode
    static constraints = {
        userId index: 'audit_user_idx'
        type index: 'audit_type_idx'
        entryDate sqlType: 'timestamp', index: 'audit_entrydate_idx'
        entryText sqlType:"longtext"
        entryCode sqlType: ""
    }
}

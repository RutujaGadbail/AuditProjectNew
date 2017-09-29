import auditprojectnew.Audit

class BootStrap {

    def init = { servletContext ->
        new Audit(type: "type1", userId: 1234, entryText: "entryText", entryDate:12/7/2017, entryCode: "entryCode" ).save()
    }

    def destroy = {
    }
}

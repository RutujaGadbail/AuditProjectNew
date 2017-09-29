import auditprojectnew.Audit

class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {

            }
        }

        "/"(view:"/index")
        "500"(view:'/error')

        "/api/log" (controller: "audit",action: "log",method:"post")

	}
}

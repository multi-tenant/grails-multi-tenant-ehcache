import grails.plugin.multitenant.ehcache.TestUser
import grails.plugin.multitenant.core.util.TenantUtils

class BootStrap
{
  def init =
  {servletContext ->
    // Create some new Users and then get a list for caching and run some get operations to fill cache
    TenantUtils.doWithTenant(1)
            {
              new TestUser(userName: "scryan", firstName: "Scott", lastName: "Ryen").save()
              new TestUser(userName: "scryan2", firstName: "Scott", lastName: "Ryen").save()
              new TestUser(userName: "scryan3", firstName: "Scott", lastName: "Ryen").save()
              new TestUser(userName: "scryan4", firstName: "Scott", lastName: "Ryen").save()
              TestUser.get(1)
              TestUser.get(2)
              TestUser.get(3)
              TestUser.get(4)
              TestUser.list([cache: true])
            }
  }
  def destroy =
  {
    log.info "Processing destroy of bootstrap"
  }
}
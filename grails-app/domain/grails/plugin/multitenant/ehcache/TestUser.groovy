package grails.plugin.multitenant.ehcache
/**
 * This is a sample user element for testing the caching front end.
 */
class TestUser
{
  static constraints =
  {
  }
  static mapping =
  {
    cache true
  }
  /** The user name for this user.  */
  String userName
  /** The first name of the user  */
  String firstName
  /** The last name of the user  */
  String lastName
}

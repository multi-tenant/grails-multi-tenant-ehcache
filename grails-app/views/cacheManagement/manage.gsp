<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title><g:message code="cacheManagement.manage.title"/></title>
</head>
<body>
<div>
  <h2><g:message code="cacheManagement.manage.heading"/></h2>
  <div>
    <g:link class="button-link" action="manage"><g:message code="cacheManagement.home.link"/></g:link>
    <g:link class="button-link" action="clearAllStatistics"><g:message code="cacheManagement.clearAllStatistics.link"/></g:link>
    <g:link class="button-link" action="clearAllContents"><g:message code="cacheManagement.clearAllContents.link"/></g:link>
  </div>
  <div>
    <g:render template="cacheSummary" model="[cacheSummary:cacheSummary]"/>
  </div>
  <div>
    <g:render template="tenantCacheSummary" model="[cacheSummaryList:cacheSummaryList]"/>
  </div>
</div>
</body>
</html>

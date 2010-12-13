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
    <g:link class="button-link" action="clearTenantCacheStatistics" params="[tenantId:tenantId]"><g:message code="cacheManagement.clearTenantCacheStatistics.link"/></g:link>
    <g:link class="button-link" action="clearTenantCacheContents" params="[tenantId:tenantId]"><g:message code="cacheManagement.clearTenantCacheContents.link"/></g:link>
  </div>
  <div>
    <g:render template="cacheSummary" model="[cacheSummary:cacheSummary]"/>
  </div>
  <div>
    <g:render template="cacheDetailSummary" model="[cacheDetailList:cacheDetailList,tenantId:tenantId]"/>
  </div>
</div>
</body>
</html>

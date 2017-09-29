<%--
  Created by IntelliJ IDEA.
  User: rutuja.gadbail
  Date: 19-09-2017
  Time: 11:35
--%>
%{--<%@ page contentType="text/html;charset=UTF-8" %>--}%
%{--<html>--}%
%{--<head>--}%
    %{--<title></title>--}%
    %{--<style type="text/css" media="screen">--}%
        %{--body{--}%
            %{--background-color: lightgray;--}%
        %{--}--}%
    %{--</style>--}%
%{--</head>--}%

%{--<body>--}%


%{--<p><h3>Results</h3></p>--}%
%{--<table class="table-two" border="1">--}%
%{--<thead>--}%
%{--<tr>--}%
%{--<th>Date</th>--}%
%{--<th>Audit type</th>--}%
%{--<th>Audit entry</th>--}%
%{--<th>User name</th>--}%
%{--<th>Audit entry code</th>--}%
%{--</tr>--}%
%{--</thead>--}%
%{--<tbody><g:each in="${auditList}" status="i" var="listAudit">--}%
    %{--<tr>--}%
        %{--<td>${listAudit.entryDate}</td>--}%
        %{--<td>${listAudit.type}</td>--}%
        %{--<td>${listAudit.entryText}</td>--}%
        %{--<td>${listAudit.userName}</td>--}%
        %{--<td>${listAudit.entryCode}</td>--}%
    %{--</tr>--}%
%{--</g:each>--}%
%{--<div>--}%
    %{--<p>--}%

        %{--<g:link url="${request.getHeader('referer')}"> Back </g:link>--}%

    %{--</p>--}%
%{--</div>--}%

%{--</tbody>--}%
%{--</table>--}%

%{--</body>--}%
%{--</html>--}%



<%--
  Created by IntelliJ IDEA.
  User: rutuja.gadbail
  Date: 30-08-2017
  Time: 15:05
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>

    <title>Audit Log</title>
    <style>

    div.displaytable {
        display: table;
        border-spacing: 5px;
        border-collapse: separate;
    }
div.canvasjs-chart-container{
    position: relative;
    alignment: center;
    object-position: center;
}
    #chartContainerelemen {
                        height: 50px;
                        width: 15%;
                    }
    div.displaytablecell {
        display: table-cell;
    }

    p.displaytablecell {
        display: table-cell;
    }

    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'audit.css')}" type="text/css">--}%
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'audit.css')}" type="text/css">



  </head>

 <body>

 <div>
     <g:form name="auditLog" url="[controller:'audit',action:'list']">
        <h1>Audit Log</h1>
        <div class="displaytable">
            <div class="displaytablecell">
                <h3>Audit entry types:</h3>
            </div>
            <div class="displaytablecell">
                <g:radioGroup name="auditTypesToShow"
                              labels="['All','Filtered']"
                              values="[1,2]"
                              value="2">
                    <p class="displaytablecell">${it.label} ${it.radio}</p>
                </g:radioGroup>
            </div>

            <div class="displaytablecell">
       </div>
                <g:select name="auditEventToShow" from="${auditEventTypes}"/>
            </div>
        <div class="displaytable">
            <div class="displaytablecell">
                <h3>Audit Entry Application:</h3>
            </div>
            <div class="displaytablecell">
                <g:radioGroup name="appNames"
                              labels="['All','Filtered']"
                              values="[1,2]"
                              value="1">
                    <p class="displaytablecell">${it.label} ${it.radio}</p>
                </g:radioGroup>
            </div>
            <div class="displaytablecell">
                <g:select name="auditAppToShow" from="${auditEntryCode}"/>
            </div>
        </div>
        <div class="displaytable">
            <div class="displaytablecell">
                <h3>Date range(Default today):</h3>
            </div>
            <div> <g:datePicker name="fromDate" value="${todayStart}" noSelection="['':'-Choose-']"/>
            to <g:datePicker name="toDate" value="${new Date()}" noSelection="['':'-Choose-']"/> </div>
        </div>
        <div class="displaytable">
            <div class="displaytablecell">
                <h3>User name:</h3>
            </div>
            <div class="displaytablecell">
                <g:radioGroup name="users"
                              labels="['All','Filtered']"
                              values="[1,2]"
                              value="1">
                    <p class="displaytablecell">${it.label} ${it.radio}</p>
                </g:radioGroup>
            </div>
            <div class="displaytablecell">

             <g:select name="userName" from="${userNames}"/>
            </div>
        </div>
        <div class="displaytable">
            <div class="displaytablecell">
                <h3>Order:</h3>
            </div>
            <div class="displaytablecell">
                <g:radioGroup name="order"
                              labels="['Ascending','Descending']"
                              values="[1,2]"
                              value="2">
                    <p class="displaytablecell">${it.label} ${it.radio}</p>
                </g:radioGroup>
            </div>
        </div>
        <div class="displaytable">
            <div class="displaytablecell">
                <h3>Max entries (defaults to 10):</h3>
            </div>
            <div class="displaytablecell">
                <g:textField name="maxEntriesToShow" />
            </div>
        </div>
        <div class="displaytable">
            <div class="displaytablecell">
                <g:submitButton name="showLog" value="Show entries" />
            </div>
        </div>
        <g:if test="${auditEntries.size()>0}">


            %{--<div id="piechart"></div>--}%

            <div id="chartContainer" style="height: 10px; width: 10px;"></div>
            <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
            <p><h3 style="margin-top: -20px">Results</h3></p>
            <table class="table-two" border="1">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Audit type</th>
                    <th>Audit entry</th>
                    <th>User name</th>
                    <th>Audit entry code</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${auditEntries}" status="i" var="auditEntry">
                    <tr>
                        <td>${auditEntry.entryDate}</td>
                        <td>${auditEntry.type}</td>
                        <td>${auditEntry.entryText}</td>
                        <td>${auditEntry.userName}</td>
                       <td>${auditEntry.entryCode}</td>
                    </tr>
                </g:each>
         </tbody>
            </table>
        </g:if>
    </g:form>
</div>
<div>
    <p>
        <g:link controller="home">Home</g:link>
    </p>
</div>

</body>
</html>
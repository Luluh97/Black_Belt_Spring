<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src='http://code.jquery.com/jquery-1.10.2.min.js'></script> 
    <script type="text/javascript">
        $(document).ready(function(){
            $('#LowToHigh').hide();
            $('#HighToLow').show();
            
             $('#LH').click(function(e){ 
                      e.preventDefault()
                      $('#HighToLow').hide();
                      $('#LowToHigh').show();
                                        });

             $('#HL').click(function(e){ 
                 e.preventDefault()
                 $('#LowToHigh').hide();
                 $('#HighToLow').show();
                                   });
             
                                });
    </script>
<meta charset="UTF-8">
<title>Task Manager</title>
</head>
<body>


    <form id="logoutForm" method="POST" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="submit" value="Logout!" />
    </form>
	
    <h1>Welcome,  <c:out value="${currentUser.name}"></c:out></h1>
        
	<a id="HL" href="">High-to-Low</a>
	<a id="LH" href="">Low-to-High</a>



    <table id="HighToLow">
    <thead>
        <tr>
            <th>Task</th>
            <th>Creator</th>
            <th>Assignee</th>
            <th>Priority</th>         
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${tasksH}" var="task">
        <tr>
        	<td><a href="/tasks/${task.id}"><c:out value="${task.task1}"/></a></td>
            <td><c:out value="${task.creator}"/></td>
            <td><c:out value="${task.assignee}"/></td>
            <td><c:out value="${task.priority}"/></td>
        </tr>
        </c:forEach>
                <c:forEach items="${tasksM}" var="task">
        <tr>
        	<td><a href="/tasks/${task.id}"><c:out value="${task.task1}"/></a></td>
            <td><c:out value="${task.creator}"/></td>
            <td><c:out value="${task.assignee}"/></td>
            <td><c:out value="${task.priority}"/></td>
        </tr>
        </c:forEach>
                <c:forEach items="${tasksL}" var="task">
        <tr>
        	<td><a href="/tasks/${task.id}"><c:out value="${task.task1}"/></a></td>
            <td><c:out value="${task.creator}"/></td>
            <td><c:out value="${task.assignee}"/></td>
            <td><c:out value="${task.priority}"/></td>
        </tr>
        </c:forEach>
    </tbody>
</table>

<table id="LowToHigh">
    <thead>
        <tr>
            <th>Task</th>
            <th>Creator</th>
            <th>Assignee</th>
            <th>Priority</th>
          
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${tasksL}" var="task">
        <tr>
        	<td><a href="/tasks/${task.id}"><c:out value="${task.task1}"/></a></td>
            <td><c:out value="${task.creator}"/></td>
            <td><c:out value="${task.assignee}"/></td>
            <td><c:out value="${task.priority}"/></td>
        </tr>
        </c:forEach>
                <c:forEach items="${tasksM}" var="task">
        <tr>
        	<td><a href="/tasks/${task.id}"><c:out value="${task.task1}"/></a></td>
            <td><c:out value="${task.creator}"/></td>
            <td><c:out value="${task.assignee}"/></td>
            <td><c:out value="${task.priority}"/></td>
        </tr>
        </c:forEach>
                <c:forEach items="${tasksH}" var="task">
        <tr>
        	<td><a href="/tasks/${task.id}"><c:out value="${task.task1}"/></a></td>
            <td><c:out value="${task.creator}"/></td>
            <td><c:out value="${task.assignee}"/></td>
            <td><c:out value="${task.priority}"/></td>
        </tr>
        </c:forEach>
    </tbody>
</table>


<a href="/tasks/new">Create Task</a>

</body>
</html>
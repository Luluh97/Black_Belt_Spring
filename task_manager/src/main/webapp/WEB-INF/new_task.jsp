<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
 
<h1>New Task</h1>

<p>${a}</p>

<form:form action="/tasks/new" method="post" modelAttribute="task">
    <p>
        <form:label path="task1">Task</form:label>
        <form:errors path="task1"/>
        <form:input path="task1" required="required"/>
    </p>
 	<p>
 	<form:label path="assignee"> Assignee
		    <form:errors path="assignee"/>
		    <form:select path="assignee">
		    		<c:forEach items="${users}" var="user">
					<form:option value="${user.name}">${user.name}</form:option>
				</c:forEach>
	</form:select></form:label>
 	</p>
    <p>
		<form:label path="priority">Priority</form:label>
		<form:errors path="priority"></form:errors>
		<form:select path="priority">
			<form:options items="${priorities}"/>
		</form:select>
    </p>   
    <p>
        <form:label path="creator"></form:label>
        <form:errors path="creator"/>
        <form:input path="creator" value="${currentUser.name}" type="hidden"/>
    </p>  
    <input type="submit" value="Submit"/>
</form:form>   
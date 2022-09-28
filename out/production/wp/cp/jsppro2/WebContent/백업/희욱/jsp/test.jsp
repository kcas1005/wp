<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%-- <% request.setCharacterEncoding("UTF-8"); %> <!-- post방식. 한글깨짐 방지 --> --%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script src="/ckeditor/ckeditor.js"></script>
    </head>
<body>
<form>
	<textarea name="editor1" id="editor1" rows="10" cols="80">
	    This is my textarea to be replaced with CKEditor.
	</textarea>
	<script>
	// Replace the <textarea id="editor1"> with a CKEditor
	// instance, using default configuration.
		CKEDITOR.replace( 'editor1' );
	</script>
</form>
</body>
</html>
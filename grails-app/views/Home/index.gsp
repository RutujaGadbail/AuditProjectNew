<%--
  Created by IntelliJ IDEA.
  User: rutuja.gadbail
  Date: 30-08-2017
  Time: 15:31
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Audit Home</title>
    <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <style>

     div.displaytable {
        display: table;
        border-spacing: 5px;
        border-collapse: separate;
    }

    div.displaytablecell {
        display: table-cell;
    }

    p.displaytablecell {
        display: table-cell;
    }


navbar-brand {
    float: left;
    height: 50px;
    padding: 15px 15px;
    font-size: 18px;
    line-height: 20px;
}
     a{
         align-items: center;
         font-size: 20px;
}
img{

 height: 700px;
 width: 1600px;
}
     .button {

  border-radius: 2px;
  background-color: #f4511e;
  border: none;
  color: #FFFFFF;
  text-align: center;
  font-size: 18px;
  padding: 20px;
  width: 200px;
  transition: all 0.5s;
  cursor: pointer;
  margin: 10px;
          nav-right: auto;
          margin-left: 800px;
}

.button span {
  cursor: pointer;
  display: inline-block;
  position: relative;
  transition: 0.5s;
}

.button span:after {

  content: '\00bb';
  position:relative;
  opacity: 0;
  top: 0;
  right: -20px;
  transition: 0.5s;
}

.button:hover span {
  padding-right: 25px;
}

.button:hover span:after {
  opacity: 1;
  right: 0;
}
</style>

</head>
<div>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="#"><h1>Welcome To Audit</h1></a>
    </div>

      <div>

          <button class="button"><span><g:link controller="audit" action="list">Goto</g:link>
</span></button>

 </div>
 </div>
</nav>
<div>


</div>
</div>

    <img src="${resource(dir: 'images', file: 'img.jpg')}" alt=""/>
</body>
</html>












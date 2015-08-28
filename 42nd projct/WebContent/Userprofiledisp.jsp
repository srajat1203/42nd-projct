<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <div class="jumbotron">
    <h1 align = "center">${name}</h1>      
    <p align = "center">${moto}</p>
    <p align = "center"><i>Join Date: </i>${jd}</p>
    
  </div>
  
  
  <form role="form" action= Userprofile method="post">
    <div class="form-group">
      <label for="searchp">Search for word:</label>
      <input type="text" class="form-control" id="searchp" name="searchp" placeholder="Enter word">
    </div>
    <button type="submit" class="btn btn-default">Search</button>
  </form>
  
  <ul class="list-group">
    <li class="list-group-item">${tmp}</li>
  </ul>   
</div>

</body>
</html>
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


${postform}
 ${err}
 
 <br>
 
 <form role="form" action= rwmblogs method="post">
    <div class="form-group">
      <label for="search">Search for word:</label>
      <input type="text" class="form-control" id="search" name="search" placeholder="Enter word">
    </div>
    <button type="submit" class="btn btn-default">Search</button>
  </form>
 

 <table class="table">
    <thead>
      <tr>
        <th>Post</th>
        <th>User</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>${tmp}</td>
        <td>${users}</td>
      </tr>
    </tbody>
  </table>


</body>
</html>
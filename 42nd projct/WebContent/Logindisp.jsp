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
  <h2>Enter post</h2>
  <form role="form" action="Login">
    <div class="form-group">
      <label for="post">email:</label>
      <input type="email" class="form-control" id="email" name ="email" placeholder="Enter email">
    </div>
    
    <button type="submit" class="btn btn-default">Submit</button>
  </form>

 <br><br>
  <a href="rwmblogs" class="btn btn-info" role="button">View mPosts</a>

${loginerr}
</div>
 
</body>
</html>
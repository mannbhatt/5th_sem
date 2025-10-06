<?php
// Database connection
$host = "localhost";
$user = "root"; 
$pass = "";     
$db   = "registration_db";	
$conn = mysqli_connect($host, $user, $pass, $db);
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
if (isset($_GET['delete'])) {
    $id = $_GET['delete'];
    mysqli_query($conn, "DELETE FROM users WHERE id=$id");
    header("Location: registration.php");
    exit(); 
}
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['register'])) {
    $name     = $_POST['name'];
    $dob      = $_POST['dob'];
    $gender   = $_POST['gender'];
    $email    = $_POST['email'];
    $mobile   = $_POST['mobile'];
    $address  = $_POST['address'];
    $state    = $_POST['state'];
    $education= $_POST['education'];
    $hobbies  = implode(", ", $_POST['hobbies']);
    $imageName = $_FILES['image']['name'];
    $imageTmp  = $_FILES['image']['tmp_name'];
    $uploadDir = "uploads/";
    if (!is_dir($uploadDir)) mkdir($uploadDir);
    move_uploaded_file($imageTmp, $uploadDir . $imageName);
    mysqli_query($conn, "INSERT INTO users (name,dob,gender,email,mobile,address,state,education,hobbies,image)
            VALUES ('$name','$dob','$gender','$email','$mobile','$address','$state','$education','$hobbies','$imageName')");
    header("Location: registration.php");
    exit();
}
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['update'])) {
    $id       = $_POST['id'];
    $name     = $_POST['name'];
    $dob      = $_POST['dob'];
    $gender   = $_POST['gender'];
    $email    = $_POST['email'];
    $mobile   = $_POST['mobile'];
    $address  = $_POST['address'];
    $state    = $_POST['state'];
    $education= $_POST['education'];
    $hobbies  = implode(", ", $_POST['hobbies']);
    mysqli_query($conn, "UPDATE users SET name='$name', dob='$dob', gender='$gender', email='$email',
            mobile='$mobile', address='$address', state='$state', education='$education', hobbies='$hobbies'
            WHERE id=$id");
    header("Location: registration.php");
    exit();
}
$editData = null;
if (isset($_GET['edit'])) {
    $id = $_GET['edit'];
    $result = mysqli_query($conn, "SELECT * FROM users WHERE id=$id");
    $editData = mysqli_fetch_assoc($result);
}
?>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif; 
            background: #f4f7f8;
            margin: 0; 
            padding: 20px;
        }
        h1, h2 {
            text-align: center; 
            color: #333;
        }
        form {
            background: #fff; 
            padding: 20px; 
            border-radius: 10px;
            max-width: 500px; 
            margin: 20px auto;
            box-shadow: 0px 0px 10px #ccc;
        }
        label { font-weight: bold; }
        input, select, textarea {
            width: 100%; 
            padding: 8px; 
            margin: 6px 0 15px 0; 
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type=radio], input[type=checkbox] {
            width: auto; 
            margin-right: 5px;
        }
        input[type=submit] {
            background: #28a745; 
            color: white; 
            border: none; 
            padding: 10px; 
            border-radius: 5px; 
            cursor: pointer;
            font-size: 16px;
        }
        input[type=submit]:hover {
            background: #218838;
        }
        table {
            border-collapse: collapse; 
            width: 100%; 
            margin-top: 30px;
        }
        table, th, td {
            border: 1px solid #ccc; 
        }
        th, td {
            text-align: center; 
            padding: 10px;
        }
        th {
            background: #007bff; 
            color: white;
        }
        tr:nth-child(even) { background: #f2f2f2; }
        a {
            text-decoration: none; 
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
        img {
            border-radius: 50%;
        }
    </style>
</head>
<body>
    <h1><?= $editData ? "Update User" : "User Registration Form"; ?></h1>
    <form action="" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<?= $editData['id'] ?? ''; ?>">
        <label>Full Name:</label>
        <input type="text" name="name" value="<?= $editData['name'] ?? ''; ?>" required>
        <label>Date of Birth:</label>
        <input type="date" name="dob" value="<?= $editData['dob'] ?? ''; ?>" required>
        <label>Gender:</label><br>
        <input type="radio" name="gender" value="male" <?= (isset($editData['gender']) && $editData['gender']=='male')?'checked':''; ?>> Male
        <input type="radio" name="gender" value="female" <?= (isset($editData['gender']) && $editData['gender']=='female')?'checked':''; ?>> Female
        <input type="radio" name="gender" value="other" <?= (isset($editData['gender']) && $editData['gender']=='other')?'checked':''; ?>> Other<br><br>
        <label>Email:</label>
        <input type="email" name="email" value="<?= $editData['email'] ?? ''; ?>" required>
        <label>Mobile:</label>
        <input type="tel" name="mobile" pattern="[0-9]{10}" value="<?= $editData['mobile'] ?? ''; ?>" required>

        <label>Address:</label>
        <textarea name="address" required><?= $editData['address'] ?? ''; ?></textarea>
        <label>State:</label>
        <select name="state" required>
            <option value="">Select</option>
            <option value="maharashtra" <?= (isset($editData['state']) && $editData['state']=='maharashtra')?'selected':''; ?>>Maharashtra</option>
            <option value="karnataka" <?= (isset($editData['state']) && $editData['state']=='karnataka')?'selected':''; ?>>Karnataka</option>
            <option value="delhi" <?= (isset($editData['state']) && $editData['state']=='delhi')?'selected':''; ?>>Delhi</option>
            <option value="gujarat" <?= (isset($editData['state']) && $editData['state']=='gujarat')?'selected':''; ?>>Gujarat</option>
        </select>
        <label>Education:</label>
        <select name="education" required>
            <option value="">Select</option>
            <option value="high-school" <?= (isset($editData['education']) && $editData['education']=='high-school')?'selected':''; ?>>High School</option>
            <option value="graduate" <?= (isset($editData['education']) && $editData['education']=='graduate')?'selected':''; ?>>Graduate</option>
            <option value="post-graduate" <?= (isset($editData['education']) && $editData['education']=='post-graduate')?'selected':''; ?>>Post Graduate</option>
        </select>
        <label>Hobbies:</label><br>
        <?php $selectedHobbies = isset($editData['hobbies']) ? explode(", ", $editData['hobbies']) : []; ?>
        <input type="checkbox" name="hobbies[]" value="reading" <?= in_array("reading",$selectedHobbies)?'checked':''; ?>> Reading
        <input type="checkbox" name="hobbies[]" value="sports" <?= in_array("sports",$selectedHobbies)?'checked':''; ?>> Sports
        <input type="checkbox" name="hobbies[]" value="music" <?= in_array("music",$selectedHobbies)?'checked':''; ?>> Music<br><br>
        <label>Profile Image:</label>
        <input type="file" name="image" accept="image/*" <?= $editData?'':'required'; ?>><br><br>

        <input type="submit" name="<?= $editData?'update':'register'; ?>" value="<?= $editData?'Update':'Register'; ?>">
    </form>
    <h2>All Registered Users</h2>
    <table>
        <tr>
            <th>ID</th><th>Name</th><th>Email</th><th>Mobile</th><th>State</th><th>Education</th><th>Hobbies</th><th>Image</th><th>Actions</th>
        </tr>
        <?php
        $result = mysqli_query($conn, "SELECT * FROM users");
        while ($row = mysqli_fetch_assoc($result)) {
            echo "<tr>
                    <td>{$row['id']}</td>
                    <td>{$row['name']}</td>
                    <td>{$row['email']}</td>
                    <td>{$row['mobile']}</td>
                    <td>{$row['state']}</td>
                    <td>{$row['education']}</td>
                    <td>{$row['hobbies']}</td>
                    <td><img src='uploads/{$row['image']}' width='60'></td>
                    <td>
                        <a href='registration.php?edit={$row['id']}'>Edit</a> | 
                        <a href='registration.php?delete={$row['id']}' onclick='return confirm(\"Delete this record?\")'>Delete</a>
                    </td>
                  </tr>";
        }
        ?>
    </table>
</body>
</html>

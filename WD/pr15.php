<?php
function isPrime($num) {
    if ($num <= 1) {
        return false;
    }
    if ($num == 2) {
        return true;
    }
    if ($num % 2 == 0) {
        return false;
    }
    for ($i = 3; $i <= sqrt($num); $i += 2) {
        if ($num % $i == 0) {
            return false;
        }}
    return true;
}
$number = 29;
if (isPrime($number)) {
echo "$number is a Prime number.";
} else {
echo "$number is NOT a Prime number.";
}?>


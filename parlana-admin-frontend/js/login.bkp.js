var app = angular.module('myApp',[]);

app.controller('myController',function($scope, $http){

	// $scope.user={'username':'','password':''};
  $scope.cn = '';
	$scope.cc = '';
  $scope.cp = '';
  $scope.showError = false; // set Error flag
	$scope.showSuccess = false; // set Success Flag

  console.log("Starting ...");
  console.log("Developed by Mirko J. Rodriguez");

	//----- Users json
	// var validUsers= [
	// 	{'username':'chandler@friends.com', 'password':'1234'},
	// 	{'username':'ross@friends.com', 'password':'1234'},
	// 	{'username':'joey@friends.com', 'password':'1234'},
	// 	{'username':'rechal@friends.com', 'password':'1234'}
	// ];

	// $scope.showError = false; // set Error flag
	// $scope.showSuccess = false; // set Success Flag

	// //------- Authenticate function
	// $scope.authenticate = function (){
	// 	var flag= false;
  //
	// 	for(var i in validUsers){ // loop on users array
	// 		if($scope.user.username == validUsers[i].username && $scope.user.password == validUsers[i].password){
	// 			flag = true;
	// 			break;
	// 		}
	// 		else{
	// 			flag = false;
	// 		}
	// 	}
  //
	// 	//-------- set error or success flags
	// 	if(flag){
	// 		$scope.showError = false;
	// 		$scope.showSuccess = true;
	// 	}
	// 	else{
	// 		$scope.showError = true;
	// 		$scope.showSuccess = false;
	// 	}
  //
  //
	// }


  $scope.authenticate2 = function(){
    // var flag = false;
    console.log("Authenticating ...");
    console.log($scope.cn);
    console.log($scope.cc);
    // endPoint = "http://18.217.126.228:8080/parlana-admin-backend/s/centralMaster/getObj";
    endPoint = "http://localhost:8080/parlana-admin-backend/s/centralMaster/getObj";
    var myService = $http({
  			method: "POST",
  			url: endPoint,
  			dataType: "json",
        data: JSON.stringify({ "centralNumber": $scope.cn, "centralCode": $scope.cc,"centralAccessPwd": $scope.cp }),
  			headers: { "Content-Type": "application/json" }
  	});

  	myService.success(function (data, status) {
  		  console.log("success");
  			console.log(data);
    		$scope.showSuccess = true;
    		$scope.showError = false;
        $scope.centralMaster = data;
  	});

  	myService.error(function (data, status) {
      	$scope.showError = true;
      	$scope.showSuccess = false;
  			// alert("Error: Contact Administrator");
  	});

  };

});

var app = angular.module('myApp',[]);

app.controller('myController',function($scope, $http){

	// $scope.user={'username':'','password':''};
  $scope.cn = '';
	$scope.cc = '';
  $scope.cp = '';
  $scope.showLogin = true;
  $scope.showError = false; // set Error flag
	$scope.showSuccess = false; // set Success Flag
  $scope.loading = false;
  $scope.rp = "http://18.217.126.228:8080/parlana-admin-backend/s";
//$scope.rp = "http://localhost:8080/parlana-admin-backend/s";

  console.log("Starting ...");
  console.log("Developed by Mirko J. Rodriguez");

  $scope.authenticate2 = function(){
    console.log("Authenticating ...");
    console.log($scope.cn);
    console.log($scope.cc);
    $scope.loading = true;
    $scope.showSuccess = false;
    $scope.showError = false;
    endPoint = $scope.rp + "/centralMaster/getObj";
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
        $scope.loading = false;
        $scope.showLogin = false;
    		$scope.showSuccess = true;
    		$scope.showError = false;
        $scope.centralMaster = data;
  	});

  	myService.error(function (data, status) {
        $scope.loading = false;
      	$scope.showError = true;
      	$scope.showSuccess = false;
        $scope.showLogin = true;
  			// alert("Error: Contact Administrator");
  	});

  };





  $scope.updateCentralMaster = function(){
    endPoint = $scope.rp + "/centralMaster/updateObj";
    var updateCM = $http({
        method: "POST",
        url: endPoint,
        dataType: "json",
        data: JSON.stringify({ "idCentralNumber":$scope.centralMaster.idCentralNumber,
                               "centralNumber": $scope.centralMaster.centralNumber,
                               "centralNumberPstn": $scope.centralMaster.centralNumberPstn,
                               "centralLang": $scope.centralMaster.centralLang }),
        headers: { "Content-Type": "application/json" }
    });

    updateCM.success(function (data, status) {
        console.log("success");
        console.log(data);
        $scope.info = "Central Master updated Successfully!";
    });

    updateCM.error(function (data, status) {
        alert("Error: Contact Administrator");
    });

  };



  $scope.clearInfo = function(){
		$scope.info = "";
	};




	$scope.updateExtension = function(ext){
		endPoint = $scope.rp + "/extension/updateObj";
		var updateCM = $http({
				method: "POST",
				url: endPoint,
				dataType: "json",
				data: JSON.stringify({ "idExtension": ext.idExtension,
															 "extensionNumber":ext.extensionNumber,
															 "extensionPersonEmail": ext.extensionPersonEmail,
															 "extensionPersonPhone":  ext.extensionPersonPhone,
														 	 "extensionIntentConfidence":  ext.extensionIntentConfidence}),
				headers: { "Content-Type": "application/json" }
		});

		updateCM.success(function (data, status) {
				console.log("success");
				console.log(data);
				$scope.info = "Extension updated Successfully!";
		});

		updateCM.error(function (data, status) {
				alert("Error: Contact Administrator");
		});

	};





  $scope.listCallEvents = function(){
		endPoint = $scope.rp + "/callEvent/getObjList";
		// alert(endPoint);
	  // alert($scope.cn);
		var listCallEvents = $http({
				method: "POST",
				url: endPoint,
				dataType: "json",
				data: JSON.stringify({ "callEventTo": $scope.cn, "callEventToCode": $scope.cc }),
				headers: { "Content-Type": "application/json" }
		});

		listCallEvents.success(function (data, status) {
				console.log("success");
				console.log(data);
				$scope.callEvents = data;
		});

		listCallEvents.error(function (data, status) {
				console.log("Error");
		});

	};






	$scope.viewDetails = function(call){
    $scope.info = "";
    $scope.selectedCall = call;

		var chart = new Chart(document.getElementById("radar-chart"), {
		    type: 'radar',
		    data: {
		      labels: ["Tristeza", "Alegria", "Miedo", "Disgusto", "Enfado"],
		      datasets: [{
		          label: "Emotion",
		          fill: true,
		          backgroundColor: "rgba(255,99,132,0.2)",
		          borderColor: "rgba(255,99,132,1)",
		          pointBorderColor: "#fff",
		          pointBackgroundColor: "rgba(255,99,132,1)",
		          pointBorderColor: "#fff",
		          data: [$scope.selectedCall.callEventFinalEmotionSadness,
										 $scope.selectedCall.callEventFinalEmotionJoy,
										 $scope.selectedCall.callEventFinalEmotionFear,
										 $scope.selectedCall.callEventFinalEmotionDisgust,
										 $scope.selectedCall.callEventFinalEmotionAnger]
		        }
		      ]
		    },
		    options: {
		      title: {
		        display: false,
		        text: 'Powered by Watson'
		      }
		    }
		});

	};







    $scope.selectExtension = function(ext){
  		console.log(ext);
      $scope.info = "";
      $scope.selectedExt = ext;
  	};

  // $scope.reloadPage = function(){
  //   window.location.reload();
  // }

});

var surveyformcontroller=surveyformapp.controller('surveyformcontroller',function($scope,$window,$http){
    $scope.about_survey=true;
	$scope.otp_valdation=false;
	$scope.ph_number =/^\+?\d{10}$/;
	$scope.otp_pattern=/^\+?\d{6}$/;
	$scope.name_pattern=/^[a-zA-Z. ]*[a-zA-Z]{1,60}$/;
	
	$scope.mobileno="";
	$scope.otp_message="";
	$scope.enter_otp=false;
	$scope.send_otp=false;
	$scope.otp="";
	$scope.user={};
	
	$scope.part_1=true;
	$scope.part_2=false;
	$scope.part_3=false;
	
	$scope.surveyformdata={
		sessionId : '',
		name : '',
		age : '',
		gender : 'Male',
		mobileNo : '',
		professional_qualification : '',
		ddc: 'Degree',
		yop : '',
		registrationNo : '',
		designation : 'Director',
		yoe : '',
		hphc : '',
		opdradio : 'Yes',
		ipdradio : 'Yes',
		mmtradio : 'Yes',
		noofas : '',
		participationinipd : '',
		participationinopd : '',
		salaryType : 'central_government',
		increment : '',
		grossSalary : '',
		nonPracticingAllowance : 'No'
	}
	
	$scope.savesurveyform=function()
	{
		//alert(JSON.stringify($scope.surveyformdata));
		var parameter={
			method : 'POST',
			url : '/Survey/savesurveyformdata',
			data : $scope.surveyformdata,
			'Content-Type' : 'application/json;charset=utf-8'
		};
		$http(parameter).then(function(response){
			var result=response.data.result;
			if( result=='success')
			   alert("You feedback data has been provisionally saved for further processing.");
		    else
				alert("You feedback data has not been saved.");
		});
	}
	
	// var parameter={
		// method : 'GET',
		// url : '/Survey/getsession',
		// 'Content-Type' : 'application/json;charset=utf-8'
	// };
	// $http(parameter).then(function(response){
		
		// $scope.surveyformdata.mobileNo=response.data.mobileNo;
		// $scope.surveyformdata.sessionId=response.data.sessionId;
	// });
	$scope.getstarted=function($event)
	{
		$event.preventDefault();
		$scope.about_survey=false;
		$scope.otp_validation=true;
		$scope.send_otp=false;
		
	}

    $scope.sendmobileno=function()
	{
		//alert($scope.mobileno);
		var data5={
			mobileNo : $scope.mobileno
		};
		var parameter={
			method : 'POST',
			url : '/Survey/sendotp',
			data : data5,
			'Content-Type' : 'application/json;charset=utf-8'
		};
		
		$http(parameter).then(function(response){
			$scope.user=response.data;
			if( $scope.user.mobileNo==$scope.mobileno )
			{
				$scope.otp_message="You OTP has been send to mobile no "+$scope.mobileno+ ". Please Enter OTP. ";
				$scope.enter_otp=true;
			}
		});
		
		//$scope.send_opt=true;
	}
	
	$scope.sendotp=function()
	{
	   if( $scope.otp==$scope.user.otp ) 
	   {
		   //alert("OTP match");
		   var parameter={
			   method : 'POST',
			   url : '/Survey/verifyuseratotp',
			   data : $scope.user,
			   'Content-Type' : 'application/json;charset=utf-8'
		   };
			$http(parameter).then(function(response){
				alert("X="+JSON.stringify(response.data));
				var result=response.data.result;
				alert(result);
				if( result=='success' ){
					    alert("result=success");
						var landingUrl = "http://" + $window.location.host + "/Survey/redirectto";
						$window.location.href = landingUrl;
				}
			});
       }
       else {
		   alert("OTP does not match.");
       }		   
	}
});
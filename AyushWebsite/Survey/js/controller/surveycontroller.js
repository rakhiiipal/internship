var surveycontroller=surveyapp.controller('surveycontroller',function($scope,$window,$http){
    $scope.about_survey=true;
	$scope.otp_valdation=false;
	$scope.ph_number =/^\+?\d{10}$/;
	$scope.otp_pattern=/^\+?\d{6}$/;
	$scope.mobileno="";
	$scope.otp_message="";
	$scope.enter_otp=false;
	$scope.send_otp=false;
	$scope.otp="";
	$scope.user={};
	$scope.part_1=true;
	$scope.part_2=false;
	$scope.part_3=false;
	
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
				//alert("X="+JSON.stringify(response.data));
				var result=response.data.result;
				//alert(result);
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
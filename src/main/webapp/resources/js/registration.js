/**
 * 
 */
var app = angular.module("register", [])
                 .controller("registration",function($scope,$http){
                	 
                $scope.zipcode="";	
                
                $scope.uExists=false;
             
                $scope.findAddress=function(zip)
                {
                	
                	 if($scope.zipcode.length==5)
                		 {
                		 $http.get('https://maps.googleapis.com/maps/api/geocode/json?address='+zip+'&sensor=true').
                	        success(function(data) {
                	            $scope.state=data.results[0].address_components[3].short_name;
                	            $scope.city=data.results[0].address_components[1].short_name;
                	        });
                		 }
                };
                
                $scope.checkUserName=function(userName)
                {
          		  
          		
          		  $http({
          			    url: 'checkUserName.htm',
          			    method: 'POST',
          			    headers: {'Content-Type': 'application/x-www-form-urlencoded'},
          			    data: {"userName": userName},
          			    transformRequest: function(obj) {
          			        var str = [];
          			        for(var p in obj)
          			        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
          			        return str.join("&");
          			      }
          			}).success(function(response) {
          			      if(response.ue)
          			    	$scope.uExists=true;
          			      else
          			    	$scope.uExists=false;
          			});
                };
              
                 });



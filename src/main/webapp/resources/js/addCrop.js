/**
 * 
 */
var app=angular.module("addCrop",[])
               .controller("addCropController",function($scope,$http){
            	   
            	   $scope.regex="\\d+";
            	   $scope.getCartSize=function()
         	      {
         	    	$http.get("getCartSize.htm").success(function(data) { 
         	    		 $scope.cartSize=data.tp;
             		  });
         	       };
            	   
            	   
            	   
     });
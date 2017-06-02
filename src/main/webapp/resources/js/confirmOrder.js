/**
 * 
 */

var app=angular.module("confirmOrder",[])
               .controller("confirmOrderController",function($scope,$http){
            	      
            	   $scope.cartSize;
            	   $scope.getCartSize=function()
         	      {
         	    	$http.get("getCartSize.htm").success(function(data) { 
         	    		 $scope.cartSize=data.tp;
             		  });
         	       } 
            	   
          
               });
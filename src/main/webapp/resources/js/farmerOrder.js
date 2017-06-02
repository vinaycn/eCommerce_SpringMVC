/**
 * 
 */

var app=angular.module("farmerOrders",['angularUtils.directives.dirPagination'])
               .controller("farmerOrderController",function($scope,$http){
            	   
            	   $scope.cartSize;
            	   $scope.getCartSize=function()
         	       {
         	    	$http.get("getCartSize.htm").success(function(data) { 
         	    		 $scope.cartSize=data.tp;
             		  });
         	       } 
            	   $scope.getOrdersOfFarmer=function(pn)
            	   {
            		   $http.get("getFarmerOrders/"+pn+"/").success(function(data) {   
           	    		  $scope.orders=data.orders;
           	    		   $scope.totalCount=data.count;
           	    		  
            			   $scope.getCartSize();
               		  });
            	   }
                    	   
               });
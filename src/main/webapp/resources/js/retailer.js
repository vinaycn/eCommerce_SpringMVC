/**
 * 
 */

var app=angular.module("retailer",['angularUtils.directives.dirPagination'])
        .controller("retailerController",function($scope,$http){
        	
        	$scope.ds=false;
	    	$scope.df=false;
	    	$scope.bidBtn=true;
        	 $scope.getCrops=function(pageNo)
       	    { 
        		 
       		 
        	     $http.get("getAllCrops/"+pageNo+"/").success(function(data) { 
        	    	 $scope.totalCount=data.tp;
        	    	 $scope.crops=data.crops;
        	    	
         		  });
       	    };
       	 $scope.cropDetails=function(crop)
  	    {
  	    	$scope.name=crop.name;
  	    	$scope.quantity=crop.quantity;
  	    	$scope.price=crop.expectedPrice;
  	    	$scope.id=crop.id;
  	    };
        	
  	    $scope.placeBid=function()
  	    {
  	    	var id=$scope.id;
  	    	var price=$scope.yourPrice;
  	    	$http.get("placeBid/"+id+"/"+price+"/").success(function(data) { 
  	    	      if(data.mes)
  	    	     {
  	    	       $scope.ds=true;
  	    	       $scope.mes=data.sucm;
  	    	     }
  	    	      else
  	    	    {
  	    	      $scope.df=true;
  	    	      $scope.mes="Bids Placed Successfully";
  	    	    }
    		  });  	
  	    };
  	       $scope.clearFields=function()
	      {
  		    $scope.yourPrice="";
	    	$scope.ds=false;
	    	$scope.df=false;
	    	$scope.mes="";
	       };
	       
	       $scope.updateBtn=function()
	       {
	    	   if(($scope.yourPrice===undefined||$scope.yourPrice==null)||($scope.yourPrice==0||$scope.yourPrice<0))
	    	   {
	    		   $scope.bidBtn=true;
	           }
	    	   else
	    	    {
	    		   $scope.bidBtn=false;
	    		}
	       };
  	    
        });
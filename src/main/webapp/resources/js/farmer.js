/**
 * 
 */

var app=angular.module('farmer',['angularUtils.directives.dirPagination'])
               .controller('farmerController',function($http,$scope){
            	        
            	  
            	      $scope.getCropCategory=function()
            	      {
            	    	
            	    	$http.get("getCropCategories.htm").success(function(data){ 
            	    		alert(data.cats);
            	    		 $scope.categories=data.cats;
                		  });
            	      };
            	      $scope.getCartSize=function()
               	      {
               	    	$http.get("getCartSize.htm").success(function(data) { 
               	    		 $scope.cartSize=data.tp;
                   		  });
               	      };
            	   
            	   $scope.getCrops=function(pageNo)
              	    { 
               	              	   
	               	     $http.get("getFarmerCrops/"+pageNo+"/").success(function(data) {  
	               	    	 $scope.totalCount=data.tp;
	               	    	 $scope.crops=data.crops;
	               	    	 $scope.getCartSize();
	               	    	 $scope.getCropCategory();
	                		});
              	    };  
              	
              	  
              	  $scope.bidDetails=function(id)
              	  {
              		 $scope.id=id;
              		$http.get("getBidsForCrop/"+id+"/").success(function(data) { 
              			
          	    		 $scope.bids=data.bids;
              		  });
              	  };
              	
              	  $scope.changeStatusToAccept=function(bid)
              	  {
                      alert("sdsad");
              		  var stat="Accept";
              		  var id=bid.id;
              		  var cId=$scope.id;
              		$http.get("updateBidStatus/"+stat+"/"+id+"/").success(function(data){ 
              			alert(data.suc)
              			if(data.suc)
              			bid.status=stat;
              			$scope.bidDetails(cId);
              		  });
              	  };
              	  
              	  $scope.changeStatusToReject=function(bid)
              	  {
              		 var id=bid.id;
              		 var cId=$scope.id;
              		 var stat="Reject";
               		$http.get("updateBidStatus/"+stat+"/"+id+"/").success(function(data) { 
               			if(data.suc)
               			bid.status=stat;
               			$scope.bidDetails(cId);
               		  });
              	  };
              	    
               });
               
               
               
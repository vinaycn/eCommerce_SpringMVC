/**
 * 
 */
var app=angular.module("retailerBids",['angularUtils.directives.dirPagination'])
        .controller("retailerBidController",function($scope,$http){
        	
        	$scope.getBids=function(pn)
        	{
        		$http.get("getBidsRetailer/"+pn+"/").success(function(data){
        			$scope.bids=data.bids;
        			$scope.totalCount=data.tb;        			
        		});
        	};
        	
        	$scope.farmerDetails=function(bid)
        	{
        		
        		$scope.name=bid.crop.farmer.person.name;
        		$scope.emailId=bid.crop.farmer.person.emailId;
        		$scope.city=bid.crop.farmer.person.address.city;
        		$scope.st=bid.crop.farmer.person.address.streetName;
        		$scope.state=bid.crop.farmer.person.address.state;
        	};
        	
        	
        });
        	
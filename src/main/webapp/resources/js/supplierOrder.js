/**
 * 
 */


var app=angular.module("supplierOrder",['angularUtils.directives.dirPagination'])
        .controller("supplierOrderController",function($scope,$http){
        	
        	
        	$scope.changeStatus=false;
        	$scope.intial=true;
        	$scope.getOrders=function(pn)
        	{
        		$http.get("getOrders/"+pn+"/").success(function(data){
        			$scope.orders=data.order;
        			$scope.totalCount=data.to;
        		});
        		
        	};
        	
        	
        	$scope.changeStatus=function(oi)
        	{
        		var stat=oi.status;
        		var id=oi.orderItemId;
        		
        		$http.get("changeOrderStatus/"+stat+"/"+id+"/").success(function(data){
        			oi.status=data.mes;
        			
        		});
        	};
        	
        });
/**
 * 
 */

var app=angular.module("supplier",['angularUtils.directives.dirPagination'])
        .controller("supplierController",function($scope,$http){
        	
        	$scope.ds=false;
    		$scope.df=false;
        	$scope.checkedCategories=[];
        	$scope.updateBtn=false;
        	 $scope.getProducts=function(pageNo)
       	    { 
       		 $scope.current=pageNo;
        	     $http.get("getSupplierProducts/"+pageNo+"/").success(function(data) { 
        	    	 $scope.totalCount=data.tc;
        	    	 $scope.products=data.productList;
         		  });
       	    };
        	
        	
        	
        	$scope.productDetails=function(product)
        	{
	        	$scope.name=product.name;
	        	$scope.price=product.pricePerUnit;
	        	$scope.quantity=product.availableQuantity;
	        	$scope.id=product.productId;
	        	
        	};
        	
        	
        	$scope.updateProductDetails=function()
        	{
        		var price=$scope.price;
        		var quantity=$scope.quantity;
        		var id=$scope.id;
        		$scope.ds=false;
        		$scope.df=false;
        		
        		  $http.get("updateProducts/"+id+"/"+price+"/"+quantity+"/").success(function(data) { 
        			  $scope.suc=data.message;
         	    	 if($scope.suc>0) 
         	    		 {
         	    		$scope.ds=true;
         	    		 $scope.mes="Product Details Updated Successfully";
         	    		 }
         	    	 else
         	    		 {
         	    		$scope.df=true;
         	    		$scope.mes="Sorry Unable to Update";
         	    		 }
         	    	 $scope.getProducts(1);
          		  });
        	}
        	
        	$scope.clearFields=function()
        	{
        		$scope.mes="";
        		$scope.ds=false;
        		$scope.df=false;
        	};
        	$scope.checkValue=function()
        	{
        		if(($scope.price===undefined||$scope.quantity===undefined)||($scope.price==null||$scope.quantity==null))
        		$scope.updateBtn=true;
        		else
        		$scope.updateBtn=false;	
        	};
        
        });
        
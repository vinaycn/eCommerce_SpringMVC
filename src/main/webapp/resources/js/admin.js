/**
 * 
 */

var app = angular
		.module("admin", [ 'angularUtils.directives.dirPagination' ])
		.controller(
				"registeredUsers",
				function($scope, $http) {

					$scope.viewUsers = false;
					$scope.viewCategory = false;
					$scope.viewCropCategory = false;
					$scope.pcError = false;
					$scope.pcError = false;
					$scope.regex = '[a-zA-Z ]*$';
					$scope.getPersonsList = function(pageNo) {
						$scope.viewCategory = false;
						$scope.viewCropCategory = false;
						$scope.current = pageNo;
						if (pageNo === undefined || pageNo == null) {
							pageNo = 1;
						}
						$http(
								{
									url : 'getUsers.htm',
									method : 'POST',
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									},
									data : {
										"pageNo" : pageNo
									},
									transformRequest : function(obj) {
										var str = [];
										for ( var p in obj)
											str
													.push(encodeURIComponent(p)
															+ "="
															+ encodeURIComponent(obj[p]));
										return str.join("&");
									}
								}).success(function(data) {
							$scope.viewUsers = true;
							$scope.totalCount = data.tc;
							$scope.persons = data.per;

						});
					};

					$scope.activate = function(id, person) {

						$http(
								{
									url : 'activate.htm',
									method : 'POS	T',
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									},
									data : {
										"personId" : id
									},
									transformRequest : function(obj) {
										var str = [];
										for ( var p in obj)
											str
													.push(encodeURIComponent(p)
															+ "="
															+ encodeURIComponent(obj[p]));
										return str.join("&");
									}
								}).success(function(response) {
							person.userAccount.status = 'active';
						});
					};

					$scope.addCategory = function() {
						var catName = $scope.cName;

						$http(
								{
									url : 'addCategory.htm',
									method : 'POST',
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									},
									data : {
										"categoryName" : catName
									},
									transformRequest : function(obj) {
										var str = [];
										for ( var p in obj)
											str
													.push(encodeURIComponent(p)
															+ "="
															+ encodeURIComponent(obj[p]));
										return str.join("&");
									}
								}).success(function(response) {
							$scope.getCategories();
							$scope.cName = "";
						});

					};

					$scope.getCategories = function() {

						$scope.viewUsers = false;
						$scope.viewCropCategory = false;
						$http.get("getCategories.htm").success(function(data) {
							$scope.viewCategory = true;
							$scope.categories = data.pc;
						});
					};

					$scope.addCropCategory = function(vb) {

						var catName = $scope.cropCategoryName;
						$http(
								{
									url : 'addCropCategory.htm',
									method : 'POST',
									headers : {
										'Content-Type' : 'application/x-www-form-urlencoded'
									},
									data : {
										"categoryName" : catName
									},
									transformRequest : function(obj) {
										var str = [];
										for ( var p in obj)
											str
													.push(encodeURIComponent(p)
															+ "="
															+ encodeURIComponent(obj[p]));
										return str.join("&");
									}
								}).success(function(response) {
							$scope.getCropCategories();
							$scope.cropCategoryName = "";
							$scope.pcCError = false;
						});

					};

					$scope.getCropCategories = function() {

						$scope.viewCategory = false;
						$scope.viewUsers = false;
						$http.get("getCropCategories.htm").success(
								function(data) {
									$scope.viewCropCategory = true;
									$scope.cropCategories = data.cats;

								});
					};
					
					$scope.personDetails=function(person)
					{
						$scope.name=person.name;
						$scope.zip=person.address.zipCode;
						$scope.state=person.address.state;
						$scope.city=person.address.city;
						$scope.st=person.address.streetName;
					};

				});

'use strict';
// var domain = 'http://localhost:8080';
var domain = 'http://104.199.102.220:8080';

angular.module('appApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.router',
    'ui.bootstrap',
    'ui.bootstrap.datetimepicker',
    'angular-jwt',
    'uiGmapgoogle-maps'
  ])
  .constant('DOMAIN', domain)
  .constant('API', domain + '/api')
  .config(['$stateProvider', '$urlRouterProvider', 'jwtInterceptorProvider', '$httpProvider', 'uiGmapGoogleMapApiProvider',
    function ($stateProvider, $urlRouterProvider, jwtInterceptorProvider, $httpProvider, uiGmapGoogleMapApiProvider) {

      uiGmapGoogleMapApiProvider.configure({
        v: '3.24',
        libraries: 'places' // Required for SearchBox.
      });
      $urlRouterProvider.otherwise('/');
      jwtInterceptorProvider.tokenGetter = ['$securityService', function ($securityService) {
        return $securityService.getToken();
      }];
      $httpProvider.interceptors.push('jwtInterceptor');


      $stateProvider.state('home',{
          url:'/',
          controller: 'MainCtrl',
          templateUrl:'views/pages/home.html'
      })
      .state('home.login', {
          url: 'login',
          controller: 'LoginCtrl',
          templateUrl: 'views/pages/login.html'
      })
      .state('home.about', {
        url: 'about',
        controller: 'LoginCtrl',
        templateUrl: 'views/pages/about.html'
      })
      .state('admin', {
          url: '/admin',
          controller: 'AdminCtrl',
          templateUrl: 'views/pages/admin.html'
      })
      .state('admin.employees', {
        url: '/employees',
        controller: 'EmployeeCtrl',
        templateUrl: 'views/pages/employees.html'
      })
      .state('admin.alerts', {
        url: '/alerts',
        controller: 'AlertListCtrl',
        templateUrl: 'views/pages/alert-list.html'
      })
      .state('admin.home', {
        url: '/home',
        controller: 'AdminHomeCtrl',
        templateUrl: 'views/pages/admin-home.html',
        resolve: {
          id: function ($stateParams) {
            return $stateParams.id;
          }
        }
      })
      .state('admin.location', {
        url: '/location/{id}',
        controller: 'EmployeeLocationCtrl',
        templateUrl: 'views/pages/employee-location.html',
        resolve: {
          id: function ($stateParams) {
            return $stateParams.id;
          }
        }
      })
      .state('admin.tasks', {
        url: '/tasks',
        controller: 'TasksCtrl',
        templateUrl: 'views/pages/tasks.html'
      })
      .state('admin.tasks.new', {
        url: '/new',
        controller: 'TaskFormCtrl',
        templateUrl: 'views/pages/task-form.html',
        resolve: {
          taskId: function () {
            return null;
          }
        }
      })
      .state('admin.tasks.edit', {
        url: '/edit/{taskId}',
        controller: 'TaskFormCtrl',
        templateUrl: 'views/pages/task-form.html',
        resolve: {
          taskId: function ($stateParams) {
            return $stateParams.taskId;
          }
        }
      })
      .state('admin.tasks.details', {
        url: '/{taskId}',
        controller: 'TaskCtrl',
        templateUrl: 'views/pages/task.html',
        resolve: {
          taskId: function ($stateParams) {
            return $stateParams.taskId;
          }
        }
      })
      .state('admin.cars', {
        url: '/cars',
        controller: 'CarCtrl',
        templateUrl: 'views/pages/cars.html'
      });
  }]);

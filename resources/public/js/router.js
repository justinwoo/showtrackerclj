App.Router.map(function () {
  this.resource('about');  
  this.resource('shows', function() {
    this.resource('editshow', { path: 'edit/:show_id' });
  });
});

App.ShowsRoute = Ember.Route.extend({  
  model: function() {
    return this.store.findAll('show');
  }
});

App.EditshowRoute = Ember.Route.extend({
  model: function(params) {
    return this.store.find('show', params.show_id);
  }
});

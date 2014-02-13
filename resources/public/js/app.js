App = Ember.Application.create();
App.Store = DS.Store.extend({
  // adapter: DS.FixtureAdapter
});

App.ShowsController = Ember.ArrayController.extend({
  deleteConfirmation: false,
  deleteId: null,
  deleteTitle: null,
  rowNewTitle: null,
  rowNewEpisode: 1,

  actions: {
    increment: function (item) {
      var episode = parseInt(item.get('episode'));
      episode += 1;
      item.set('episode', episode);
      item.save();
    },
    decrement: function (item) {
      var episode = parseInt(item.get('episode'));
      episode -= 1;
      item.set('episode', episode);
      item.save();
    },
    createShow: function () {
      var self = this;
      var newTitle = this.get('rowNewTitle');
      var newEpisode = this.get('rowNewEpisode');
      var newShow = this.store.createRecord('show', {
        title: newTitle,
        episode: newEpisode
      });
      this.transitionTo('shows', newShow.save());
    },
    delete: function (item) {
      var deleteId = this.get('deleteId');
      var self = this;
      // i guess in this case, i have to make sure to ask for the result of the find first...?
      var show = this.store.find('show', deleteId).then(function (item) {
        item.deleteRecord();
        item.save();
        self.set('deleteConfirmation', false);
      }, function (err) {
        console.log(err);
      });
      
    },
    confirmDelete: function (item) {
      this.set('deleteConfirmation', true);
      this.set('deleteId', item.get('id'));
      this.set('deleteTitle', item.get('title')); 
    },
    cancelDelete: function () {
      this.set('deleteConfirmation', false);
    }
  }
});

App.EditshowController = Ember.ObjectController.extend({
  newTitle: null,
  newEpisode: null,
  actions: {
    doneEditing: function (item) {
      var newTitle = this.get('newTitle');
      var newEpisode = this.get('newEpisode');
      if (newTitle != null) {
        item.set('title', newTitle);
      }
      if (newEpisode != null) {
        item.set('episode', newEpisode);
      }      
      item.save();
    }
  }
});

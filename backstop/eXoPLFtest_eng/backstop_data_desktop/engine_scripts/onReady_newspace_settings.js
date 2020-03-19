// onReady example
module.exports = function(casper, scenario, vp) {
  // Example: Adding script delays to allow for things like CSS transitions to complete.
  casper.thenOpen(scenario.url);
//ou avec casper 2
casper.then( function(){
    casper.echo('loggin');
    if (this.exists('div.loginButton')) {
    casper.waitForSelector('form', function(){
      this.fillSelectors('form', {
        'input[id="username"]':'root',
        'input[id="password"]':'gtn'
      }, true);
    casper.echo('Clicking button');
    casper.click('button.button');
    casper.wait(8000);
    });
   }
  });

casper.then( function(){ 
    casper.echo('create spacefor newspace_settings');
    casper.click('div.joinSpace a');
    casper.wait(3000);

  });

casper.then( function(){
    casper.echo('Clicking button');
    casper.click('button.btn.btn-primary.pull-left');
    casper.echo('button clicked');
    casper.echo('waiting for popup');
    casper.waitForSelector('div.uiPopupMySpaces');
    casper.echo('popup showed');
    if (this.exists('div.uiPopupMySpaces')) {
        this.echo('the uiPopupMySpaces exists');
    }

  });

casper.then( function(){
  casper.waitForSelector('div.UIPopupWindow.uiPopup.UIDragObject.NormalStyle form.UIForm', function(){
      this.fillSelectors('div.UIPopupWindow.uiPopup.UIDragObject.NormalStyle form.UIForm', {
        'input[id="displayName"]':'test23'
      });
    casper.click('div.uiAction button.btn');
    casper.wait(8000);

      });
  });

casper.thenOpen(scenario.url + "/portal/g/:spaces:test23/test23", function() {
    casper.echo('Space Page is displayed');
    casper.wait(4000);
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');
  });

casper.thenOpen(scenario.url + "/portal/g/:spaces:test23/test23/settings", function() {
    casper.echo('Clicking Space Settings button');
    casper.waitForSelector('div.uiSpaceSettingPortlet');
    casper.wait(4000);
    casper.echo('newSpace settings');
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });

}
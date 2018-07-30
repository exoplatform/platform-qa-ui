module.exports = function(casper, scenario, vp) {
  // Example: Adding script delays to allow for things like CSS transitions to complete.
  casper.thenOpen(scenario.url);

if (vp.label === 'desktop') {

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
    casper.echo('create spacefor calendar');
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
        'input[id="displayName"]':'test1001'
      }, true);
    casper.wait(1000);
    
      });
  });

casper.then( function(){
    casper.echo('Clicking button');
    casper.click('div.uiAction button.btn');
    casper.waitForSelector('div.uiSpaceMenu');
    casper.wait(1000);

  });

casper.then( function(){  
    casper.echo('Clicking calendar button');
    casper.click('span#calendar');
    casper.waitForSelector('div.calendarWorkingWorkspace');
    casper.wait(4000);
    casper.echo('newSpace calendar');
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });
}

else {
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
    casper.echo('Clicking button menu');
    casper.click('div.mobile-visible.toggle-left-bar');
    casper.wait(1000);

  });

casper.then( function(){ 
    casper.echo('Clicking button spaces');
    casper.click('h5.title.accordionBar.clearfix a');
    casper.wait(3000);

  });

casper.then( function(){
    casper.echo('Clicking SpaceTest button');
    casper.click('div.itemList.spaceList.clearfix a.limitText');
    casper.echo('button clicked');
    casper.waitForSelector('div.uiSpaceMenu');
    casper.wait(5000);
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');
  });

casper.then( function(){  
    casper.echo('Clicking forum button');
    casper.click('span#calendar');
    casper.waitForSelector('span#calendar');
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });
}

}
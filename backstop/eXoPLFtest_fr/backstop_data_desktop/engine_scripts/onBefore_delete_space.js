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
    casper.echo('delete space');
    casper.echo('clicking mySpaces button');
    casper.click('i.uiIconSpaceNavigation');
    casper.wait(3000);

  });

casper.then( function(){
    casper.echo('Clicking button delete');
    casper.click('button.confirmPopup.btn.pull-right');
    casper.echo('button clicked');
    casper.echo('waiting for popup');
    casper.waitForSelector('div.UIPopupWindow.UIDragObject.uiPopup');
    casper.echo('popup showed');
    if (this.exists('div.UIPopupWindow.UIDragObject.uiPopup')) {
        this.echo('the UIPopupWindow exists');
    }
    casper.wait(3000);
  });



casper.then( function(){  
    casper.echo('Clicking button');
    casper.click('div.uiAction.uiActionBorder a');
    casper.wait(3000);
    casper.echo('Space deleted');
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });
}

else {
  casper.thenOpen(scenario.url +"/intranet/spaces", function() {  
    casper.echo('Clicking button');
    casper.echo('Space deleted');
    casper.wait(3000);
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });
}

}

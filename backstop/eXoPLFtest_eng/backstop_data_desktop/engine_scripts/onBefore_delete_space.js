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
    casper.echo('delete space');
    casper.echo('clicking mySpaces button');
    casper.click('i.uiIconSpaceNavigation');
    casper.wait(3000);

  });

casper.then( function(){
    casper.echo('Clicking button delete');
    casper.waitForSelector('button.confirmPopup.btn.pull-right');
    casper.click('button.confirmPopup.btn.pull-right');
    casper.echo('button clicked');
    casper.echo('waiting for popup');
    casper.waitForSelector('div.UIPopupWindow.UIDragObject.uiPopup:nth-of-type(2)');
    casper.echo('popup showed');
    if (this.exists('div.UIPopupWindow.UIDragObject.uiPopup:nth-of-type(2)')) {
        this.echo('the UIPopupWindow exists');
    }
    casper.wait(3000);
  });



casper.then( function(){  
    casper.echo('Clicking button');
    casper.click('div.uiAction.uiActionBorder a:nth-of-type(1)');
    casper.wait(3000);
    casper.echo('Space deleted');
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });

}
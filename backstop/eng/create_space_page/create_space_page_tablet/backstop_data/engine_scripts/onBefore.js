// onReady example
module.exports = function(casper, scenario, vp) {
  // Example: Adding script delays to allow for things like CSS transitions to complete.
  casper.thenOpen(scenario.url);
//ou avec casper 2
   
  
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
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });

}


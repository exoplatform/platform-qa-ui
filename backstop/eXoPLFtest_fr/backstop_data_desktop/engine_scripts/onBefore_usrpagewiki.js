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
    casper.echo('Clicking wiki button');
    casper.click('i#MenuItemwiki.uiIconWiki');
    casper.waitForSelector('i#MenuItemwiki.uiIconWiki');
    casper.wait(4000);
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
    casper.echo('Clicking user button');
    casper.click('div#UIUserPlatformToolBarPortlet a');
    casper.waitForSelector('i.uiIconPLFProfile');

  });

casper.then( function(){  
    casper.echo('Clicking userwiki button');
    casper.click('i.uiIconWikiMyWiki');
    casper.waitForSelector('div.uiIconAppprofile.uiIconDefaultApp');
    casper.wait(4000);
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });
}

}

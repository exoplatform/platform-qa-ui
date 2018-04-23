module.exports = function(casper, scenario, vp) {
  // Example: Adding script delays to allow for things like CSS transitions to complete.
  casper.thenOpen(scenario.url);

casper.then( function(){
    casper.echo('loggin');
    casper.waitForSelector('form', function(){
      this.fillSelectors('form', {
        'input[class="username"]':'root',
        'input[class="password"]':'gtn'
      }, true);
    casper.echo('Clicking button');
    casper.click('button.button');
    casper.wait(8000);
    });

  });

casper.then( function(){  
    casper.echo('Clicking user button');
    casper.click('div#UIUserPlatformToolBarPortlet a');
    casper.waitForSelector('i.uiIconPLFProfile');
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });

casper.then( function(){  
    casper.echo('Clicking userwiki button');
    casper.click('i.uiIconWikiMyWiki');
    casper.waitForSelector('div.uiWikiPageContentArea.wikiBox');
    this.capture('C:/Backstopjs/Backstopjs Projects/DemoExo/backstop_data/user-action-screenshots/' + vp.name+'.png');

  });

}

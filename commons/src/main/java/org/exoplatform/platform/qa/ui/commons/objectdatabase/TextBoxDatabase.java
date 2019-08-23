package org.exoplatform.platform.qa.ui.commons.objectdatabase;

import java.util.ArrayList;
import java.util.Random;

public class TextBoxDatabase {
  public ArrayList<Integer> type;

  public ArrayList<String>  content;

  /**
   * TextBoxDatabase
   * 
   * @param type String
   * @param content String
   */
  public TextBoxDatabase(ArrayList<Integer> type, ArrayList<String> content) {
    this.type = type;
    this.content = content;
  }

  /**
   * TextBoxDatabase
   */
  public TextBoxDatabase() {
    type = new ArrayList<Integer>();
    content = new ArrayList<String>();
  }

  /**
   * setContentData
   * 
   * @param userDataFile String
   * @param userSheet String
   * @param opParams String
   * @throws Exception String
   */

  /**
   * Get file name by index
   * 
   * @param index int
   * @return content.get(index)
   */
  public String getContentByIndex(int index) {
    return content.get(index);
  }

  /**
   * get file name random
   * 
   * @return Content
   */
  public String getContentRandom() {
    Random randomGenerator = new Random();
    int index = randomGenerator.nextInt(this.content.size());
    String Content = this.content.get(index);
    return Content;
  }

  /**
   * getArrayContentByType
   * 
   * @param type int
   * @return arrayContent
   */
  public ArrayList<String> getArrayContentByType(int type) {
    ArrayList<String> arrayContent = new ArrayList<String>();
    for (int i = 0; i < this.type.size(); i++) {
      if (this.type.get(i) == type) {
        arrayContent.add(this.content.get(i));
      }
    }
    return arrayContent;
  }

  /**
   * getArrayContentByArrayType
   * 
   * @param type int
   * @return arrayContent
   */
  public ArrayList<String> getArrayContentByArrayType(int... type) {
    ArrayList<String> arrayContent = new ArrayList<String>();
    for (int j = 0; j < type.length; j++) {
      for (int i = 0; i < this.type.size(); i++) {
        if (this.type.get(i) == type[j]) {
          arrayContent.add(this.content.get(i));
        }
      }
    }
    return arrayContent;
  }

  /**
   * getContentByArrayTypeRandom
   * 
   * @param type int
   * @return Content
   */
  public String getContentByArrayTypeRandom(int type) {
    ArrayList<String> arrayContent = new ArrayList<String>();
    Random randomGenerator = new Random();
    for (int i = 0; i < this.type.size(); i++) {
      if (this.type.get(i) == type)
        arrayContent.add(this.content.get(i));
    }
    int index = randomGenerator.nextInt(arrayContent.size());
    String Content = arrayContent.get(index).trim();
    ;
    return Content;
  }
}

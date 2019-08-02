import com.spire.doc.*;
import com.spire.doc.documents.DocumentObjectType;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextRange;
import com.spire.doc.interfaces.ICompositeObject;
import com.spire.doc.interfaces.IDocumentObject;
import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Author：yeleifeng
 * @Time: 2019-6-18
 * @Description: docx文档转换
 */

public class Docx {

    public static void main(String[] args)throws Exception{
          File file=getNewDocx("/Users/ylf/IdeaProjects/picture/src/test.docx");
          System.out.print(file.getName());
    }

    public  static Document load(String address){   //加载docx文档
        Document doc=new Document();
        try{
            doc.loadFromFile(address);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return doc;
    }

    public  static String getText(String address){  //传入docx文档地址，返回String类型docx文档文字部分
          Document doc=load(address);
          if(doc!=null){
              return doc.getText();
          }
          else{
              return null;
          }
    }

    public static File getNewDocx(String address)throws Exception{ //传入docx文档地址，返回转化后的docx文档文件
          Document doc=load(address);
          if(doc!=null){
              //创建Queue对象
              Queue nodes = new LinkedList();
              nodes.add(doc);
              //遍历文档中的子对象
              while (nodes.size() > 0) {
                  ICompositeObject node = (ICompositeObject) nodes.poll();
                  for (int i = 0; i < node.getChildObjects().getCount(); i++) {
                      IDocumentObject child = node.getChildObjects().get(i);
                      if (child instanceof ICompositeObject) {
                           nodes.add((ICompositeObject) child);
                          //获取图片并添加到List
                          if (child.getDocumentObjectType() == DocumentObjectType.Picture) {
                               DocPicture picture = (DocPicture) child;
                               File file=new File(getPath()+"/temp/pic.png");
                               ImageIO.write((RenderedImage)picture.getImage(),"PNG",file);
                               int index=node.getChildObjects().indexOf(child);
                               node.getChildObjects().remove(child);
                               TextRange text=new TextRange(child.getDocument());
                               text.setText("latex公式");
                               node.getChildObjects().insert(index,text);
                          }
                      }
                  }
              }
              doc.saveToFile("new.docx");
              //将图片保存为PNG格式文件
              return new File("new.docx");
          }
          else{
              return null;
          }
    }

    public static String getPath(){  //返回图片文件保存的地址，项目src下的img文件夹
        File base=new File("");
        File dir=new File(base.getAbsolutePath()+"/src/img");
        if(!dir.exists()){
            dir.mkdirs();
        }
        return dir.getAbsolutePath();
    }


}

package dodd.cotro.core;

import static org.objectweb.asm.Opcodes.NEW;

import java.util.Iterator;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;

import net.minecraft.launchwrapper.IClassTransformer;

public class Transformer implements IClassTransformer {
	
	@Override
	public byte[] transform(String name, String transformedName, byte[] bytes) {
		if (name.equals("com.teamacronymcoders.base.registrysystem.Registry")) {
			ClassReader reader = new ClassReader(bytes);
			ClassNode node = new ClassNode();
			reader.accept(node, 0);
			
			Iterator<MethodNode> methods = node.methods.iterator();
			while(methods.hasNext()) {
				MethodNode m = methods.next();
				
				/*
				public com.teamacronymcoders.base.registrysystem.Registry(java.lang.String, com.teamacronymcoders.base.IBaseMod);
				Code:
				   0: aload_0
				   1: invokespecial #23		// Method java/lang/Object."<init>":()V
				   4: aload_0
				   5: new           #25		// class java/util/HashMap
				   8: dup
				   9: invokespecial #26		// Method java/util/HashMap."<init>":()V
				  12: putfield      #28		// Field entries:Ljava/util/Map;
				  15: aload_0
				  16: getstatic     #33		// Field com/teamacronymcoders/base/registrysystem/LoadingStage.PREINIT:Lcom/teamacronymcoders/base/registrysystem/LoadingStage;
				  19: putfield      #35		// Field loadingStage:Lcom/teamacronymcoders/base/registrysystem/LoadingStage;
				  22: aload_0
				  23: aload_1
				  24: putfield      #37		// Field name:Ljava/lang/String;
				  27: aload_0
				  28: aload_2
				  29: putfield      #39		// Field mod:Lcom/teamacronymcoders/base/IBaseMod;
				  32: return
				*/
				
				if (m.name.equals("<init>")) {
					boolean found = false;
					AbstractInsnNode i = null;
					TypeInsnNode nw = null;
					InsnNode dup = null;
					MethodInsnNode is = null;
					
					Iterator<AbstractInsnNode> iter = m.instructions.iterator();
					while (iter.hasNext()) {
						i = iter.next();
						
						if (i.getOpcode() == NEW) {
							nw = (TypeInsnNode) i;
							iter.remove();
							dup = (InsnNode) iter.next();
							iter.remove();
							is = (MethodInsnNode) iter.next();
							iter.remove();
							i = iter.next();
							
							found = true;
							break;
						}
					}
					
					System.out.println("Found BASE Registry class! Changing entries from HashMap to LinkedHashMap...");
					
					if (found) {
						m.instructions.insertBefore(i, new TypeInsnNode(nw.getOpcode(), "java/util/LinkedHashMap"));
						m.instructions.insertBefore(i, new InsnNode(dup.getOpcode()));
						m.instructions.insertBefore(i, new MethodInsnNode(is.getOpcode(), "java/util/LinkedHashMap", new String(is.name), new String(is.desc), is.itf));
					}
				}
			}
			
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			node.accept(writer);
			return writer.toByteArray();
		}
		else {
			return bytes;
		}
	}
}

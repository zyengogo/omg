package com.omg.xbase.struts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.ser.BeanPropertyWriter;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.codehaus.jackson.map.type.ClassKey;
import org.codehaus.jackson.map.util.ArrayBuilders;

public class FilterSerializerFactory extends CustomSerializerFactory {
	/**
	 * exclude properties
	 */
	HashMap<ClassKey, String[]> _excludeFilterMappings = null;
	
	public FilterSerializerFactory() {
		super();
	}
	
	public void addExcludeFilters(String filter){
		if ((filter != null) && !"".equals(filter)) {
			if (_excludeFilterMappings == null) {
				_excludeFilterMappings = new HashMap<ClassKey, String[]>();
			}
			String[] filters = filter.split(";");
			for(String fs:filters){
				String[] classFilters = fs.split(":");
				try {
					Class<?> classz = Class.forName(classFilters[0]);
					String[] props = classFilters[1].split(",");
					_excludeFilterMappings.put(new ClassKey(classz), props);
				} catch (ClassNotFoundException e) {
				}
			}
		}
	}
	
	public void resetExcludeFilters(){
		if (_excludeFilterMappings != null) {
			_excludeFilterMappings.clear();
		}
	}
	
	@Override
	protected List<BeanPropertyWriter> filterBeanProperties(SerializationConfig config, BasicBeanDescription beanDesc, List<BeanPropertyWriter> props) {
		AnnotationIntrospector intr = config.getAnnotationIntrospector();
		AnnotatedClass ac = beanDesc.getClassInfo();
		if (_excludeFilterMappings != null) {
			String[] efm = _excludeFilterMappings.get(new ClassKey(beanDesc.getBeanClass()));
			if (efm != null && efm.length > 0) {
				HashSet<String> ignoredSet = ArrayBuilders.arrayToSet(efm);
				Iterator<BeanPropertyWriter> it = props.iterator();
				while (it.hasNext()) {
					if (ignoredSet.contains(it.next().getName())) {
						it.remove();
					}
				}
			}
		}
		String[] ignored = intr.findPropertiesToIgnore(ac);
		if (ignored != null && ignored.length > 0) {
			HashSet<String> ignoredSet = ArrayBuilders.arrayToSet(ignored);
			Iterator<BeanPropertyWriter> it = props.iterator();
			while (it.hasNext()) {
				if (ignoredSet.contains(it.next().getName())) {
					it.remove();
				}
			}
		}
		return props;
	}
}

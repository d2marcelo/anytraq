package com.brtracker.shared.utils.packet;

import static com.brtracker.shared.utils.packet.PacketDefinitionConstants.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.brtracker.shared.utils.json.Json;
import com.brtracker.shared.utils.logging.MyLogger;
import com.brtracker.shared.utils.packet.vendors.XirgoPacketReaderController;

/**
 * The Reader Controller encapsulates the logic of reading a given device packet
 * according to the corresponding schema definition. It also binds all the read fields 
 * to a dynamic Map or Maps or quasi JSON Object wrapper represented by TrackingMessage.
 * 
 * If the schema definition namespace is known use readPacket(short[] packet, String ns).
 * If the unit port is known use readPacket(short[] packet, int port).
 * If no knowledge of the namespace or port is known, use readPacket(short[] packet) and make
 * sure the packet includes a header containing the port of the unit.
 * 
 * The packet header is always optional.
 * 
 */
public class PacketReaderController {
	private MyLogger logger = new MyLogger(PacketReaderController.class);
	private static final String VOID_ELEMENT_NAME = null;
	
	private PacketDefinitionRegistry packetDefinitionRegistry;
	private PacketReaderRegistry readerRegistry;
	private XirgoPacketReaderController xirgoReader;
	
	/**
	 * Assumes the packet has 
	 */
	public TrackingMessage readPacket(short[] packet) {
		
		PacketReaderContext context = createContext(packet);

		TrackingHeader trackingHeader = processPacketHeader(context);
		
		if (needsXirgoReader(context)) {
			int from = trackingHeader.getUnitPacketStartIndex();
			int to = packet.length;
			short[] rawMessage = Arrays.copyOfRange(packet, from, to);
			logger.logInfo("Reading Xirgo packet");
			return xirgoReader.readPacket(rawMessage);
		}

		String ns = getNsFromPortOrAbort(context, trackingHeader);
		context.setNamespace(ns);
		
		preprocessPacket(context, ns);
		
		readPacketElement(context, VOID_ELEMENT_NAME, packetDefinitionRegistry.getMessageRoot(ns));
		return context.getTrackingMessage();
		
	}

	public TrackingMessage readPacket(short[] packet, int port) {
		
		PacketReaderContext context = createContext(packet);

		TrackingHeader trackingHeader = processPacketHeader(context);
		context.setPort(port);
		
		if (needsXirgoReader(context)) {
			int from = trackingHeader.getUnitPacketStartIndex();
			int to = packet.length;
			short[] rawMessage = Arrays.copyOfRange(packet, from, to);
			logger.logInfo("Reading Xirgo packet");
			return xirgoReader.readPacket(rawMessage);
		}

		String schemaNs = packetDefinitionRegistry.getSchemaNamespaceByPort(port);
		context.setNamespace(schemaNs);

		preprocessPacket(context, schemaNs);
		
		readPacketElement(context, VOID_ELEMENT_NAME, packetDefinitionRegistry.getMessageRoot(schemaNs));
		return context.getTrackingMessage();
	}
	
	/**
	 *  Reads a given device packet using the schema definition for the given namespace
	 */
	public TrackingMessage readPacket(short[] packet, String ns) {
		
		PacketReaderContext context = createContext(packet);

		processPacketHeader(context);
		context.setNamespace(ns);
		
		preprocessPacket(context, ns);
		
		readPacketElement(context, VOID_ELEMENT_NAME, packetDefinitionRegistry.getMessageRoot(ns));
		return context.getTrackingMessage();
	}
	
	/**
	 * Finds the unit identifier according to the schema "unit_identifier_propery" value using the default schema definition 
	 */
	public String findUnitIdentifier(short[] packet) {
		
		String defaultNs = packetDefinitionRegistry.getDefaultNs();
		return findUnitIdentifier(packet, defaultNs);
	}

	public String findUnitIdentifier(short[] packet, int port) {
		
		String schemaNs = packetDefinitionRegistry.getSchemaNamespaceByPort(port);
		return findUnitIdentifier(packet, schemaNs);
	}

	public Object seekFieldValue(short[] packet, int port, String fieldName) {
		String schemaNs = packetDefinitionRegistry.getSchemaNamespaceByPort(port);
		return seekFieldValue(packet, schemaNs, fieldName);
	}
	
	@SuppressWarnings("unchecked")
	public Object seekFieldValue(short[] packet, String ns, String fieldName) {
		
		Map<String,?> schemaDefinition = packetDefinitionRegistry.getSchemaDefinition(ns);
		
		Object fieldValue = null;
		Object fieldElement = schemaDefinition.get(fieldName);
		if (fieldElement == null) {
			throw new InvalidPacketDefException("Could not seek field value " + fieldName + 
					" from given packet and namespace " + PacketPrinterUtils.toHexString(packet) + " " + ns);
		}
		
		if (fieldElement instanceof Map<?,?>) {

			PacketReaderContext context = createContext(packet);
			
			processPacketHeader(context);
			context.setNamespace(ns);
			
			preprocessPacket(context, ns);
			
			Map<String,?> fieldDefinition = (Map<String,?>) fieldElement;
			Map<String,?> fieldValueInfo = (Map<String,?>) readPacketElement(context, VOID_ELEMENT_NAME, fieldDefinition);
			if (fieldValueInfo != null && fieldValueInfo.size() > 0) {
				fieldValue = getFieldValue(context, fieldDefinition, fieldValueInfo);
			}

		} else {
			throw new InvalidPacketDefException("Could not seek field value " + fieldName + 
					" is not properly defined in the schema " + ns);
		}

		return fieldValue;
	}
	
	/**
	 * Finds the unit identifier according to the schema "unit_identifier_propery" value using the given schema definition namespace
	 */
	@SuppressWarnings("unchecked")
	public String findUnitIdentifier(short[] packet, String ns) {
		
		Map<String,?> schemaDefinition = packetDefinitionRegistry.getSchemaDefinition(ns);
		
		String unitIdentifier = "";
		Object unitIdElement = schemaDefinition.get(UNIT_ID_P);
		
		if (unitIdElement instanceof String) {

			TrackingMessage message = readPacket(packet, ns);
			
			String unitIdentifierProperty = (String) unitIdElement;
			if (unitIdentifierProperty != null) {
				unitIdentifier = message.getProperty(String.class, unitIdentifierProperty);
			}
			
		} else {
			
			PacketReaderContext context = createContext(packet);
			
			Map<String,?> unitIdDefinition = (Map<String,?>) unitIdElement;
			Map<String,?> unitIdInfo = (Map<String,?>) readPacketElement(context, VOID_ELEMENT_NAME, unitIdDefinition);
			if (unitIdInfo != null && unitIdInfo.size() > 0) {
				unitIdentifier = (String) getFieldValue(context, unitIdDefinition, unitIdInfo);
			}
		}
		
		if (unitIdentifier == null) {
			throw new InvalidPacketDefException("Could not retrieve Unit Id from given packet and namespace " + PacketPrinterUtils.toHexString(packet) + " " + ns);
		}

		return unitIdentifier;
		
	}
	
	@SuppressWarnings("unchecked")
	private Object getFieldValue(PacketReaderContext context, Map<String, ?> fieldDefinition, Map<String, ?> fieldInfo) {
		
		Object fieldValue = null;
		Map<String,Object> fieldValueDefinition = (Map<String,Object>) fieldDefinition.get(FIELD_VALUE_P);
		if (fieldValueDefinition == null) {
			throw new InvalidPacketDefException("Invalid dynamic length field definition: no " + FIELD_VALUE_P + " available", context);
		}
		String targetProperty = (String) fieldValueDefinition.get(PROPERTY_P);
		if (targetProperty != null) {
			fieldValue = fieldInfo.get(targetProperty);
		}
		if (fieldValue == null) {
			if (fieldInfo.size() == 1) {
				for (String key : fieldInfo.keySet() ) {
					fieldValue = fieldInfo.get(key);
				}
			}
		}
		return fieldValue;
	}

	@SuppressWarnings("unchecked")
	private Object readPacketElement(PacketReaderContext context, String name, Map<String,?> e) {
		
		// integer based message must be initialized
		if (!context.isPacketReady()) {
			throw new InvalidPacketDefException("Invalid packet definition: message hasn't been initialized ", context);
		}
		
		if (isPrimitiveElement(e)) {
			readPrimitiveElement(context, name, e);
		} else if (isSelector(e)) {
			readSelectorElement(context, name, e);
			context.popDataObject();
		} else if (isSelector2(e)) {
			readSelector2Element(context, name, e);
			context.popDataObject();
		} else if (isFieldSet(e)) {
			readFieldSet(context, name, e);
		} else if (isArray(e)) {
			readArrayElement(context, name, e);
		} else if (isDymanicLengthField(e)) {
			readDynamicLengthField(context, name, e);
		} else if (isDirectAccess(e)) {
			readDirectAccessProperty(context, name, e);
		} else if (isTokenizedFieldSet(e)) {
			readTokenizedField(context, name, e);
		} else {
			
			Set<String> keySet = e.keySet();
			for (String key: keySet) {
				
				Object value = e.get(key);
				
				if (TYPE_P.equals(key)) {
					
					String type = (String) value;
					Map<String, ?> typeDefinition = getTypeDefinition(context, type, name, e);
					
					readPacketElement(context, type, typeDefinition);
					context.popDataObject();
					
				} else if (MULTIPLY_RULE_P.equals(key)) {
					
					applyMultiplyRule(context.getCurrentDataObject(), value);
					
				} else if (value instanceof Map<?,?>) {
					readPacketElement(context, key, (Map<String,?>) value);
				} else {
					handleElementProperty(context, key, value);
				}
			}
		}
		return context.getCurrentDataObject();
	}
	
	@SuppressWarnings("unchecked")
	private void readTokenizedField(PacketReaderContext context, String name, Map<String, ?> e) {
		
		// getting the map of fields
		Map<String,?> fields = (Map<String,?>) e.get(FIELDS_P);
		
		if (fields == null) {
			throw new InvalidPacketDefException("Invalid indexed field set definition: no fields available", context);
		}

	}

	private boolean isTokenizedFieldSet(Map<String, ?> e) {
		return TOKENIZED_FIELD_SET_P.equals(e.get(TYPE_P));
	}

	private Map<String, ?> getTypeDefinition(PacketReaderContext context, String type, String propertyName, Map<String,?> e ) {
		
		Map<String, ?> itemTypeElement = packetDefinitionRegistry.getElementDefinition(type, context.getNamespace());
		
		if (itemTypeElement == null) {
			throw new InvalidPacketDefException("Invalid packet definition: wrong type specified " + type, context);
		}
		HashMap<String, Object> nextElementMap = new HashMap<String, Object>();
		bindData(context, propertyName, e, nextElementMap);
		context.pushDataObject(nextElementMap);
		return itemTypeElement;
	}
	
	private void handleElementProperty(PacketReaderContext context, String key, Object value) {
		logger.logDebug("handling element property " + key);
		checkMessageName(context, value);
	}

	private boolean hasPreprocessor(Map<String, ?> e) {
		return e.get(PRE_PROCESSOR_P) != null ? true : false; 
	}

	private void preprocessMessage(PacketReaderContext context, Map<String, ?> e) {
		String opreprocessorKey = (String) e.get(PRE_PROCESSOR_P);
		PacketPreprocessor preprocessor = 
			packetDefinitionRegistry.getPreprocessor(opreprocessorKey);
		if (preprocessor != null) {
			preprocessor.preprocess(context, e);
		}
	}

	private boolean isPrimitiveElement(Map<String,?> e) {
		String type = (String) e.get(TYPE_P);
		return type != null ? readerRegistry.isPrimitive(type) : false; 
	}

	private boolean isSelector(Map<String,?> e) {
		return SELECTOR_P.equals(e.get(TYPE_P));
	}

	private boolean isSelector2(Map<String,?> e) {
		return SELECTOR2_P.equals(e.get(TYPE_P));
	}
	
	private boolean isArray(Map<String,?> e) {
		return ARRAY_P.equals(e.get(TYPE_P));
	}

	private boolean isFieldSet(Map<String, ?> e) {
		return FIELD_SET_P.equals(e.get(TYPE_P));
	}

	private boolean isDymanicLengthField(Map<String, ?> e) {
		return DYN_LENGTH_FIELD_P.equals(e.get(TYPE_P));
	}

	private boolean isDirectAccess(Map<String, ?> e) {
		return DIRECT_ACCESS_P.equals(e.get(TYPE_P));
	}

	private void readPrimitiveElement(PacketReaderContext context, String name, Map<String, ?> e) {
		printlnElement("Reading primitite element " + name, e);
		Object data = readerRegistry.readPrimitiveElement(context, e);
		bindData(context, name, e, data);
	}

	@SuppressWarnings("unchecked")
	private void bindData(PacketReaderContext context, String name, Map<String, ?> e, Object data) {
		if (data != null) {

			boolean store = PacketReaderUtils.getOptionalBooleanVal(e, STORE_P, true);
			if (!store) {
				return;
			}

			Object dataObject = context.getCurrentDataObject();
			if (dataObject != null && dataObject instanceof Map<?,?>) {
				Map<String, Object> dataMap = (Map<String, Object>) dataObject;
				
				logger.logDebug("binding data " + data.toString());
				
				if (String.class.isAssignableFrom(data.getClass())) {
					dataMap.put(name, ((String)data).trim());
				} else {
					dataMap.put(name, data);
				}
				
			}
		}
	}

	private void readSelectorElement(PacketReaderContext context, String name, Map<String,?> e) {
		printlnElement("Reading selector element " + name, e);
		
		@SuppressWarnings("unchecked")
		Map<String,?> mapping = (Map<String,?>) e.get(MAPPING_P);
		if (mapping == null) {
			throw new InvalidPacketDefException("Invalid selector definition: no mapping available", context);
		}
		
		Integer selectorLength = PacketReaderUtils.getOptionalIntegerVal(e, KEY_LENGTH_P);
		int i = context.readInt();
		if (selectorLength != null) {
			context.skipInts(selectorLength - 1);
		}
		String nextElement = (String) PacketReaderUtils.getByHexOrDec(mapping, i);
		
		Map<String, ?> definition = packetDefinitionRegistry.getElementDefinition(nextElement, context.getNamespace());
		
		if (definition != null) {
			
			Map<String, Object> nextElementMap = new HashMap<String, Object>();
			bindData(context, nextElement, e, nextElementMap);
			context.pushDataObject(nextElementMap);

			readPacketElement(context, nextElement, definition);
		} else {
			throw new InvalidPacketDefException("Given packet has invalid selector value " + i + "/" + Integer.toHexString(i), context);
		}
	}

	private void readSelector2Element(PacketReaderContext context, String name, Map<String,?> e) {
		printlnElement("Reading selector element " + name, e);
		
		@SuppressWarnings("unchecked")
		Map<String,?> mapping = (Map<String,?>) e.get(MAPPING_P);
		if (mapping == null) {
			throw new InvalidPacketDefException("Invalid selector definition: no mapping available", context);
		}
		
		String selectorType = PacketReaderUtils.getOptionalStringVal(e, KEY_TYPE_P);
		String selectorLen = PacketReaderUtils.getOptionalStringVal(e, KEY_LENGTH_P);
		
		String nextElement = null;
		Object selectorValue = null;
		
		if (selectorType != null && selectorLen != null) {
			
			Map<String, String> keyInfo = new HashMap<String, String>();
			keyInfo.put(TYPE_P, selectorType);
			keyInfo.put(LENGTH_P, selectorLen);
			readPrimitiveElement(context, name, keyInfo);
			Object dataObject = context.getCurrentDataObject();
			if (dataObject != null && dataObject instanceof Map<?,?>) {
				@SuppressWarnings("unchecked")
				Map<String, Object> dataMap = (Map<String, Object>) dataObject;
				selectorValue = dataMap.get(name);
				if (selectorValue != null) {
					nextElement = PacketReaderUtils.getOptionalStringVal(mapping, String.valueOf(selectorValue));
					
					Map<String, ?> definition = packetDefinitionRegistry.getElementDefinition(nextElement, context.getNamespace());
					
					if (definition != null) {
						
						Map<String, Object> nextElementMap = new HashMap<String, Object>();
						bindData(context, nextElement, e, nextElementMap);
						context.pushDataObject(nextElementMap);

						readPacketElement(context, nextElement, definition);
					} else {
						throw new InvalidPacketDefException("Given packet has invalid selector2 " +
								"nextElement/selectorValue" + nextElement + " " + selectorValue);
					}

				}
			}
			
		} else {
			throw new InvalidPacketDefException("Given packet has invalid selector2 value ", context);
		}
		
	}

	@SuppressWarnings("unchecked")
	private void readArrayElement(PacketReaderContext context, String name,
			Map<String, ?> e) {
		
		Map<String,?> lengthElement = (Map<String,?>) e.get(LENGTH_P);
		if (lengthElement == null) {
			throw new InvalidPacketDefException("Invalid packet definition: array element with no length property ", context);
		}
		Integer length = (Integer) readerRegistry.readPrimitiveElement(context, lengthElement);
		if (length == null || length < 0) {
			throw new InvalidPacketDefException("Invalid packet definition: invalid array length value ", context);
		}
		
		String itemType = (String) e.get(ITEM_TYPE_P);
		if (itemType == null) {
			throw new InvalidPacketDefException("Invalid packet definition: array element with no item_type property ", context);
		}
		
		Map<String, ?> itemTypeElement = packetDefinitionRegistry.getElementDefinition(itemType, context.getNamespace());
		
		if (itemTypeElement == null) {
			throw new InvalidPacketDefException("Invalid packet definition: invalid schema array element type specified", context);
		}
		
		boolean mappingAvailable = false;
		Map<String,?> mapping = (Map<String,?>) e.get(MAPPING_P);
		if (mapping != null) {
			mappingAvailable = true;
		}
		
		Object[] items = new Object[length];
		for (int i=0; i<length; i++) {
			context.pushDataObject(new HashMap<String, Object>());
			Object item = readPacketElement(context, itemType, itemTypeElement);
			items[i] = item;
			context.popDataObject();
			// mapping the given property id to a custom (meaningful property)
			if (mappingAvailable) {
				bindCustomProperty(context, item, mapping);
			}
		}
		bindData(context, name, e, items);
	}

	@SuppressWarnings("unchecked")
	private void bindCustomProperty(PacketReaderContext context, Object item, Map<String,?> mapping) {
		
		if (item instanceof Map<?,?>) {
			Map<String,?> itemMap = (Map<String,?>) item;
			Object propertyId = itemMap.get(PROPERTY_ID_P);
			if (propertyId != null) {
				Object p = mapping.get(String.valueOf(propertyId));
				if (p != null) {
					bindData(context, String.valueOf(p), itemMap, itemMap.get(PROPERTY_VALUE_P));
				}
			}
		}
	}
	
	private void checkMessageName(PacketReaderContext context, Object messageName) {
		if (messageName != null) {
			context.setMessageName(messageName.toString());
		}
	}
	
	/**
	 * A field set if a map of fields that can be looked up by some key. Ex:
	 * 
	 * "1": {"type":"int", "property":"dataformat"},
     * Key: 1 
     * 
     * Or 
     * "h01" : {"type":"string", "property":"manufacturerId"},
	 * Key: h01
	 * 
	 * A given field set can be optional altogether, as is the case of our custom header fields.
	 * A given field might not exists in the payload, in which case there should be no problems.
	 * A given field might appear in the payload, but not in the definition, in which case it will be skipped.
	 *
	 * The general layout field set is assumed:
	 * <field-key><field-delimiter><field-value><field-delimiter>...<field-key><field-delimiter><field-value><field-delimiter>
	 *  
	 * Several assumptions/requirements have been taken in this Field Set implementation.
	 * - Field Keys are separated by an integer delimiter this integer could be the aschii value of a given character or an actual number
	 * - Field values are also separated by the field delimiter
	 */
	@SuppressWarnings("unchecked")
	private void readFieldSet(PacketReaderContext context, String name, Map<String, ?> e) {
		printlnElement("Reading IFS element " + name, e);
		
		checkMessageName(context, (String) e.get(MESSAGE_NAME_P));
		
		// getting the map of fields
		Map<String,?> fields = (Map<String,?>) e.get(FIELDS_P);
		if (fields == null) {
			throw new InvalidPacketDefException("Invalid indexed field set definition: no fields available", context);
		}
		
		// flag indicating the whole field set is optional, which causes to rewind if none of the fields in a given payload are present.
		boolean optional = PacketReaderUtils.getOptionalBooleanVal(e, OPTIONAL_P, Boolean.FALSE);
		
		// fields are assumed to end with a delimiter value
		int fieldDelimiter = PacketReaderUtils.getOptionalIntegerVal(e, FIELD_DELIM_P, 0);
		String strFieldDelimiter = String.valueOf(fieldDelimiter);
		
		// defines the type of the field key, some could be integer other can be string
		Map<String, Object> fieldKeyType = PacketReaderUtils.getOptionalMapVal(e, FIELD_KEY_P);
		if (fieldKeyType == null) {
			throw new InvalidPacketDefException("Invalid indexed field set definition: no fieldIndex available", context);
		}
		
		// Adding a convenient delimiter to the field key type 
		fieldKeyType.put(FIELD_DELIM_P, strFieldDelimiter);
		
		boolean done = false;
		int currentPosition = 0;
		
		// just a convenient drop until definition to skipped packet data
		Map<String, Object> skipFieldValue = new HashMap<String, Object>();
		skipFieldValue.put("type", "drop_until");
		skipFieldValue.put("value", strFieldDelimiter);
		skipFieldValue.put("store", "false");
		
		while (!done) {
			context.setFieldDelimiterReached(false);
			
			if (!context.hasNextInt()) {
				break;
			}
			
			// keeping the original reading packet position in case of rewind is needed
			if (optional) {
				currentPosition = context.getCurrentPosition();
			}

			Object fieldKeyValue = null;
			try {
				// Reading the actual key value, and preventing and out of bound for optional headers
				fieldKeyValue = readerRegistry.readPrimitiveElement(context, fieldKeyType);
			} catch (PacketReadOutOfBoundary ob) {
				if (optional) {
					context.setCurrentPosition(currentPosition);
					break;
				}
			}
			
			// looking up the field definition by key value
			Map<String,Object> fieldDefinition = (Map<String,Object>) fields.get(String.valueOf(fieldKeyValue));
			
			// No field definition available 
			if (fieldDefinition == null) {
				logger.logInfo("Field Definition not found field [" + fieldKeyValue + "]");
				// optional field set, rewind to the original position and stop processing 
				if (optional) {
					context.setCurrentPosition(currentPosition);
					break;
				}
				// skipping the remaining data until next field delimiter
				readerRegistry.readPrimitiveElement(context, skipFieldValue);
				continue;
			}
			
			// checking delimiter boundary after field key
			checkDelimiter(context, "after field id " + fieldKeyValue, fieldDelimiter);
			
			// adding a convenient delimiter property to the current field definition
			fieldDefinition.put(FIELD_DELIM_P, strFieldDelimiter);
			
			Object data = null;
			try {
				// reading the actual field data according to the field definition, , and preventing and out of bound for optional headers
				data = readerRegistry.readPrimitiveElement(context, fieldDefinition);
			} catch (PacketReadOutOfBoundary ob) {
				if (optional) {
					context.setCurrentPosition(currentPosition);
					break;
				}
			}
			if (data != null) {
				// getting the target property for this field definition to perform the binding
				String targetProperty = (String) PacketReaderUtils.getOptionalStringVal(fieldDefinition, PROPERTY_P, String.valueOf(fieldKeyValue));
				bindData(context, targetProperty, fieldDefinition, data);
			} 
			// checking delimiter boundary after field data
			checkDelimiter(context, "after field data " + fieldKeyValue, fieldDelimiter);
		}
	}

	@SuppressWarnings("unchecked")
	private void readDynamicLengthField(PacketReaderContext context,
			String name, Map<String, ?> e) {
		
		Map<String,?> fieldLength = (Map<String,?>) e.get(FIELD_LENGTH_P);
		if (fieldLength == null) {
			throw new InvalidPacketDefException("Invalid dynamic length field definition: no " + FIELD_LENGTH_P + " available", context);
		}

		Map<String,Object> fieldValue = (Map<String,Object>) e.get(FIELD_VALUE_P);
		if (fieldValue == null) {
			throw new InvalidPacketDefException("Invalid dynamic length field definition: no " + FIELD_VALUE_P + " available", context);
		}

		Integer length = (Integer) readerRegistry.readPrimitiveElement(context, fieldLength);
		fieldValue.put(LENGTH_P, String.valueOf(length));

		Object data = readerRegistry.readPrimitiveElement(context, fieldValue);
		if (data != null) {
			// getting the target property for this field definition to perform the binding
			String targetProperty = (String) PacketReaderUtils.getOptionalStringVal(fieldValue, PROPERTY_P, String.valueOf(name));
			bindData(context, targetProperty, fieldValue, data);
		} 
	}

	@SuppressWarnings("unchecked")
	private void readDirectAccessProperty(PacketReaderContext context,
			String name, Map<String, ?> e) {
		
		Map<String,?> offsetDefinition = (Map<String,?>) e.get(OFFSET_P);
		if (offsetDefinition == null) {
			throw new InvalidPacketDefException("Invalid direct access field definition: no " + OFFSET_P + " available", context);
		}

		Map<String,Object> fieldValue = (Map<String,Object>) e.get(FIELD_VALUE_P);
		if (fieldValue == null) {
			throw new InvalidPacketDefException("Invalid direct access field definition: no " + FIELD_VALUE_P + " available", context);
		}
		
		readerRegistry.readPrimitiveElement(context, offsetDefinition);

		Object data = readerRegistry.readPrimitiveElement(context, fieldValue);
		if (data != null) {
			// getting the target property for this field definition to perform the binding
			String targetProperty = (String) PacketReaderUtils.getOptionalStringVal(fieldValue, PROPERTY_P, String.valueOf(name));
			bindData(context, targetProperty, fieldValue, data);
		} 
	}

	private void preprocessPacket(PacketReaderContext context, String ns) {
		
		Map<String,?> schemaDefinition = packetDefinitionRegistry.getSchemaDefinition(ns);
		boolean hasPreprocessor = hasPreprocessor(schemaDefinition); 

		if (hasPreprocessor) {
			preprocessMessage(context, schemaDefinition);
		} 
	}
	
	private TrackingHeader processPacketHeader(PacketReaderContext context) {
		
		Map<String, ?> messageRootDefinition = packetDefinitionRegistry.getMessageRoot(COMMON_SCHEMA_NS);
		readPacketElement(context, VOID_ELEMENT_NAME, messageRootDefinition);
		TrackingHeader trackingHeader = context.getTrackingHeader();
		
		String unitId = trackingHeader.getProperty(String.class, UNIT_ID);
		if (unitId != null) {
			context.setUnitId(unitId);
		}
		
		String messageDate = trackingHeader.getProperty(String.class, MESSAGE_DATE);
		if (messageDate != null) {
			context.setMessageDate(messageDate);
		}
		
		Integer port = trackingHeader.getProperty(Integer.class, "port");
		logger.logInfo("Setting port from packet header " + port);
		if (port != null) {
			context.setPort(port);
		}
		context.pushDataObject(new HashMap<String, Object>());
		return trackingHeader;
	}
	
	private String getNsFromPortOrAbort(PacketReaderContext context, TrackingHeader trackingHeader) {
		
		String schemaNs = null;
		Integer port = trackingHeader.getProperty(Integer.class, "port");
		if (port != null) {
			schemaNs = packetDefinitionRegistry.getSchemaNamespaceByPort(port);
		}
		if (schemaNs == null) {
			throw new InvalidPacketDefException("Invalid packet definition: no schema reference in packet header", context);
		}
		return schemaNs;
		
	}

	/* The rule defines the object's property name to match and the property name that carries the value to be multiplied. 
	 * For example: this rule indicates to check if the given object's property_id matches 13 or 14, then its corresponding property_value
	 * will get multiplied by 0.4 or 0.5 respectively.
	 * {property_name_selector=property_id, property_value_selector=property_value ,13=0.4, 14=0.5}
	 * 
	 * The given object
	 * {property_id=13, property_value=23.0}
	 * 
	 * Actually matches the property id 13 and therefore the property_value 23.0 will get multiplied by 0.4 
	   
	 */
	@SuppressWarnings("unchecked")
	private void applyMultiplyRule(Object object, Object rule) {
		
		if (object instanceof Map<?, ?>) {
			Map<String, Object> mapObject = (Map<String, Object>) object;
			Json jsonRule = Json.make(rule);
			String pNameSelector = jsonRule.at(PROPERTY_NAME_SELECTOR_P).asString();
			String pValueSelector = jsonRule.at(PROPERTY_VALUE_SELECTOR_P).asString();
			
			Json currentObject = Json.make(object);
			if (currentObject.has(pNameSelector) && currentObject.has(pNameSelector)) {
				
				String propertyNameValue = currentObject.at(pNameSelector).asString();
				
				if (jsonRule.has(propertyNameValue)) {
					float multiplyFactor = jsonRule.at(propertyNameValue).asFloat();
					float propertyValue = currentObject.at(pValueSelector).asFloat();
					mapObject.put(pValueSelector, Float.valueOf(multiplyFactor*propertyValue));
				}
			}
		}
	}

	private void checkDelimiter(PacketReaderContext context, String msg, int fieldDelimiter) {
		if (!context.isFieldDelimiterReached()) {
			int delimiter = context.readInt();
			if(delimiter != fieldDelimiter) {
				throw new InvalidPacketDefException("No field delimiter: " + msg, context);
			}
		}		
	}
	
	private PacketReaderContext createContext(short[] packet) {
		
		PacketReaderContext context = new PacketReaderContext();
		context.setRawPacket(packet);

		Map<String, ?> commonSchema = packetDefinitionRegistry.getSchemaDefinition(COMMON_SCHEMA_NS);
		if (commonSchema == null) {
			throw new InvalidSchemaException("Missing common schema required to parse packet header");
		}

		context.pushDataObject(new HashMap<String, Object>());
		
		boolean hasPreprocessor = hasPreprocessor(commonSchema); 

		if (hasPreprocessor) {
			preprocessMessage(context, commonSchema);
		} 
		return context;
	}
	
	private void printlnElement(String msg, Map<String, ?> e) {
		StringBuilder sb = new StringBuilder(msg + ": ");
		for (String key: e.keySet()) {
			sb.append(key + " ");
		}
		logger.logDebug(msg + " " + sb.toString());
	}
	
	public void setPacketDefinitionRegistry(
			PacketDefinitionRegistry packetDefinitionRegistry) {
		this.packetDefinitionRegistry = packetDefinitionRegistry;
	}

	public void setReaderRegistry(PacketReaderRegistry readerRegistry) {
		this.readerRegistry = readerRegistry;
	}

	private boolean needsXirgoReader(PacketReaderContext context) {
		return context != null && context.getPort() != null 
			&& context.getPort().intValue() == 8084;
	}

	public void setXirgoReader(XirgoPacketReaderController xirgoReader) {
		this.xirgoReader = xirgoReader;
	}


}

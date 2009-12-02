/**
 * JReversePro - Java Decompiler / Disassembler.
 * Copyright (C) 2008 Karthik Kumar.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 *  
 *  	http://www.apache.org/licenses/LICENSE-2.0 
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 */
package net.sf.jrevpro.ast.evaluator;

import java.util.List;

import net.sf.jrevpro.ast.expression.Constant;
import net.sf.jrevpro.reflect.instruction.Instruction;

public class IPushEvaluator extends AbstractInstructionEvaluator {

	public IPushEvaluator(EvaluatorContext context) {
		super(context);
	}

	@Override
	void evaluate(Instruction ins) {
		switch (ins.opcode) {
		case OPCODE_BIPUSH: {
			evalStack.push(new Constant(ins.getArgByte(), JVM_TYPE_BYTE));
			break;
		}
		case OPCODE_SIPUSH: {
			// Sign Extend This
			evalStack.push(new Constant(ins.getArgShort(), JVM_TYPE_SHORT));
			break;
		}
		}
	}

	@Override
	List<Integer> getProcessingOpcodes() {
		return numbersAsList(OPCODE_BIPUSH, OPCODE_SIPUSH);
	}

}
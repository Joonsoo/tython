import ast
import python3_10_pb2

def apply_abstract_mod(node: ast.mod, proto: python3_10_pb2.AbstractMod):
  match node:
    case ast.Module():
      proto.module.SetInParent()
      apply_module(node, proto.module)
    case ast.Interactive():
      proto.interactive.SetInParent()
      apply_interactive(node, proto.interactive)
    case ast.Expression():
      proto.expression.SetInParent()
      apply_expression(node, proto.expression)
    case ast.FunctionType():
      proto.function_type.SetInParent()
      apply_function_type(node, proto.function_type)

def apply_module(node: ast.Module, proto: python3_10_pb2.Module):
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)
  for x in node.type_ignores:
    v = proto.type_ignores.add()
    apply_abstract_type_ignore(x, v)

def apply_interactive(node: ast.Interactive, proto: python3_10_pb2.Interactive):
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)

def apply_expression(node: ast.Expression, proto: python3_10_pb2.Expression):
  apply_abstract_expr(node.body, proto.body)

def apply_function_type(node: ast.FunctionType, proto: python3_10_pb2.FunctionType):
  for x in node.argtypes:
    v = proto.argtypes.add()
    apply_abstract_expr(x, v)
  apply_abstract_expr(node.returns, proto.returns)

def apply_abstract_stmt(node: ast.stmt, proto: python3_10_pb2.AbstractStmt):
  match node:
    case ast.FunctionDef():
      proto.function_def.SetInParent()
      apply_function_def(node, proto.function_def)
    case ast.AsyncFunctionDef():
      proto.async_function_def.SetInParent()
      apply_async_function_def(node, proto.async_function_def)
    case ast.ClassDef():
      proto.class_def.SetInParent()
      apply_class_def(node, proto.class_def)
    case ast.Return():
      getattr(proto, "return").SetInParent()
      apply_return(node, getattr(proto, "return"))
    case ast.Delete():
      proto.delete.SetInParent()
      apply_delete(node, proto.delete)
    case ast.Assign():
      proto.assign.SetInParent()
      apply_assign(node, proto.assign)
    case ast.AugAssign():
      proto.aug_assign.SetInParent()
      apply_aug_assign(node, proto.aug_assign)
    case ast.AnnAssign():
      proto.ann_assign.SetInParent()
      apply_ann_assign(node, proto.ann_assign)
    case ast.For():
      getattr(proto, "for").SetInParent()
      apply_for(node, getattr(proto, "for"))
    case ast.AsyncFor():
      proto.async_for.SetInParent()
      apply_async_for(node, proto.async_for)
    case ast.While():
      getattr(proto, "while").SetInParent()
      apply_while(node, getattr(proto, "while"))
    case ast.If():
      getattr(proto, "if").SetInParent()
      apply_if(node, getattr(proto, "if"))
    case ast.With():
      getattr(proto, "with").SetInParent()
      apply_with(node, getattr(proto, "with"))
    case ast.AsyncWith():
      proto.async_with.SetInParent()
      apply_async_with(node, proto.async_with)
    case ast.Match():
      proto.match.SetInParent()
      apply_match(node, proto.match)
    case ast.Raise():
      getattr(proto, "raise").SetInParent()
      apply_raise(node, getattr(proto, "raise"))
    case ast.Try():
      getattr(proto, "try").SetInParent()
      apply_try(node, getattr(proto, "try"))
    case ast.Assert():
      getattr(proto, "assert").SetInParent()
      apply_assert(node, getattr(proto, "assert"))
    case ast.Import():
      getattr(proto, "import").SetInParent()
      apply_import(node, getattr(proto, "import"))
    case ast.ImportFrom():
      proto.import_from.SetInParent()
      apply_import_from(node, proto.import_from)
    case ast.Global():
      getattr(proto, "global").SetInParent()
      apply_global(node, getattr(proto, "global"))
    case ast.Nonlocal():
      getattr(proto, "nonlocal").SetInParent()
      apply_nonlocal(node, getattr(proto, "nonlocal"))
    case ast.Expr():
      proto.expr.SetInParent()
      apply_expr(node, proto.expr)
    case ast.Pass():
      getattr(proto, "pass").SetInParent()
      apply_pass(node, getattr(proto, "pass"))
    case ast.Break():
      getattr(proto, "break").SetInParent()
      apply_break(node, getattr(proto, "break"))
    case ast.Continue():
      getattr(proto, "continue").SetInParent()
      apply_continue(node, getattr(proto, "continue"))

def apply_function_def(node: ast.FunctionDef, proto: python3_10_pb2.FunctionDef):
  proto.name = node.name
  apply_arguments(node.args, proto.args)
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)
  for x in node.decorator_list:
    v = proto.decorator_list.add()
    apply_abstract_expr(x, v)
  if node.returns is not None:
    apply_abstract_expr(node.returns, proto.returns)
  if node.type_comment is not None:
    proto.type_comment = node.type_comment
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_async_function_def(node: ast.AsyncFunctionDef, proto: python3_10_pb2.AsyncFunctionDef):
  proto.name = node.name
  apply_arguments(node.args, proto.args)
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)
  for x in node.decorator_list:
    v = proto.decorator_list.add()
    apply_abstract_expr(x, v)
  if node.returns is not None:
    apply_abstract_expr(node.returns, proto.returns)
  if node.type_comment is not None:
    proto.type_comment = node.type_comment
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_class_def(node: ast.ClassDef, proto: python3_10_pb2.ClassDef):
  proto.name = node.name
  for x in node.bases:
    v = proto.bases.add()
    apply_abstract_expr(x, v)
  for x in node.keywords:
    v = proto.keywords.add()
    apply_keyword(x, v)
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)
  for x in node.decorator_list:
    v = proto.decorator_list.add()
    apply_abstract_expr(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_return(node: ast.Return, proto: python3_10_pb2.Return):
  if node.value is not None:
    apply_abstract_expr(node.value, proto.value)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_delete(node: ast.Delete, proto: python3_10_pb2.Delete):
  for x in node.targets:
    v = proto.targets.add()
    apply_abstract_expr(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_assign(node: ast.Assign, proto: python3_10_pb2.Assign):
  for x in node.targets:
    v = proto.targets.add()
    apply_abstract_expr(x, v)
  apply_abstract_expr(node.value, proto.value)
  if node.type_comment is not None:
    proto.type_comment = node.type_comment
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_aug_assign(node: ast.AugAssign, proto: python3_10_pb2.AugAssign):
  apply_abstract_expr(node.target, proto.target)
  apply_abstract_operator(node.op, proto.op)
  apply_abstract_expr(node.value, proto.value)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_ann_assign(node: ast.AnnAssign, proto: python3_10_pb2.AnnAssign):
  apply_abstract_expr(node.target, proto.target)
  apply_abstract_expr(node.annotation, proto.annotation)
  if node.value is not None:
    apply_abstract_expr(node.value, proto.value)
  proto.simple = node.simple
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_for(node: ast.For, proto: python3_10_pb2.For):
  apply_abstract_expr(node.target, proto.target)
  apply_abstract_expr(node.iter, proto.iter)
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)
  for x in node.orelse:
    v = proto.orelse.add()
    apply_abstract_stmt(x, v)
  if node.type_comment is not None:
    proto.type_comment = node.type_comment
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_async_for(node: ast.AsyncFor, proto: python3_10_pb2.AsyncFor):
  apply_abstract_expr(node.target, proto.target)
  apply_abstract_expr(node.iter, proto.iter)
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)
  for x in node.orelse:
    v = proto.orelse.add()
    apply_abstract_stmt(x, v)
  if node.type_comment is not None:
    proto.type_comment = node.type_comment
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_while(node: ast.While, proto: python3_10_pb2.While):
  apply_abstract_expr(node.test, proto.test)
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)
  for x in node.orelse:
    v = proto.orelse.add()
    apply_abstract_stmt(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_if(node: ast.If, proto: python3_10_pb2.If):
  apply_abstract_expr(node.test, proto.test)
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)
  for x in node.orelse:
    v = proto.orelse.add()
    apply_abstract_stmt(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_with(node: ast.With, proto: python3_10_pb2.With):
  for x in node.items:
    v = proto.items.add()
    apply_withitem(x, v)
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)
  if node.type_comment is not None:
    proto.type_comment = node.type_comment
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_async_with(node: ast.AsyncWith, proto: python3_10_pb2.AsyncWith):
  for x in node.items:
    v = proto.items.add()
    apply_withitem(x, v)
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)
  if node.type_comment is not None:
    proto.type_comment = node.type_comment
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_match(node: ast.Match, proto: python3_10_pb2.Match):
  apply_abstract_expr(node.subject, proto.subject)
  for x in node.cases:
    v = proto.cases.add()
    apply_match_case(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_raise(node: ast.Raise, proto: python3_10_pb2.Raise):
  if node.exc is not None:
    apply_abstract_expr(node.exc, proto.exc)
  if node.cause is not None:
    apply_abstract_expr(node.cause, proto.cause)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_try(node: ast.Try, proto: python3_10_pb2.Try):
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)
  for x in node.handlers:
    v = proto.handlers.add()
    apply_abstract_excepthandler(x, v)
  for x in node.orelse:
    v = proto.orelse.add()
    apply_abstract_stmt(x, v)
  for x in node.finalbody:
    v = proto.finalbody.add()
    apply_abstract_stmt(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_assert(node: ast.Assert, proto: python3_10_pb2.Assert):
  apply_abstract_expr(node.test, proto.test)
  if node.msg is not None:
    apply_abstract_expr(node.msg, proto.msg)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_import(node: ast.Import, proto: python3_10_pb2.Import):
  for x in node.names:
    v = proto.names.add()
    apply_alias(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_import_from(node: ast.ImportFrom, proto: python3_10_pb2.ImportFrom):
  if node.module is not None:
    proto.module = node.module
  for x in node.names:
    v = proto.names.add()
    apply_alias(x, v)
  if node.level is not None:
    proto.level = node.level
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_global(node: ast.Global, proto: python3_10_pb2.Global):
  for x in node.names:
    proto.names.append(x)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_nonlocal(node: ast.Nonlocal, proto: python3_10_pb2.Nonlocal):
  for x in node.names:
    proto.names.append(x)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_expr(node: ast.Expr, proto: python3_10_pb2.Expr):
  apply_abstract_expr(node.value, proto.value)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_pass(node: ast.Pass, proto: python3_10_pb2.Pass):
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_break(node: ast.Break, proto: python3_10_pb2.Break):
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_continue(node: ast.Continue, proto: python3_10_pb2.Continue):
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_abstract_expr(node: ast.expr, proto: python3_10_pb2.AbstractExpr):
  match node:
    case ast.BoolOp():
      proto.bool_op.SetInParent()
      apply_bool_op(node, proto.bool_op)
    case ast.NamedExpr():
      proto.named_expr.SetInParent()
      apply_named_expr(node, proto.named_expr)
    case ast.BinOp():
      proto.bin_op.SetInParent()
      apply_bin_op(node, proto.bin_op)
    case ast.UnaryOp():
      proto.unary_op.SetInParent()
      apply_unary_op(node, proto.unary_op)
    case ast.Lambda():
      getattr(proto, "lambda").SetInParent()
      apply_lambda(node, getattr(proto, "lambda"))
    case ast.IfExp():
      proto.if_exp.SetInParent()
      apply_if_exp(node, proto.if_exp)
    case ast.Dict():
      proto.dict.SetInParent()
      apply_dict(node, proto.dict)
    case ast.Set():
      proto.set_expr.SetInParent()
      apply_set_expr(node, proto.set_expr)
    case ast.ListComp():
      proto.list_comp.SetInParent()
      apply_list_comp(node, proto.list_comp)
    case ast.SetComp():
      proto.set_comp.SetInParent()
      apply_set_comp(node, proto.set_comp)
    case ast.DictComp():
      proto.dict_comp.SetInParent()
      apply_dict_comp(node, proto.dict_comp)
    case ast.GeneratorExp():
      proto.generator_exp.SetInParent()
      apply_generator_exp(node, proto.generator_exp)
    case ast.Await():
      getattr(proto, "await").SetInParent()
      apply_await(node, getattr(proto, "await"))
    case ast.Yield():
      getattr(proto, "yield").SetInParent()
      apply_yield(node, getattr(proto, "yield"))
    case ast.YieldFrom():
      proto.yield_from.SetInParent()
      apply_yield_from(node, proto.yield_from)
    case ast.Compare():
      proto.compare.SetInParent()
      apply_compare(node, proto.compare)
    case ast.Call():
      proto.call.SetInParent()
      apply_call(node, proto.call)
    case ast.FormattedValue():
      proto.formatted_value.SetInParent()
      apply_formatted_value(node, proto.formatted_value)
    case ast.JoinedStr():
      proto.joined_str.SetInParent()
      apply_joined_str(node, proto.joined_str)
    case ast.Constant():
      proto.constant.SetInParent()
      apply_constant(node, proto.constant)
    case ast.Attribute():
      proto.attribute.SetInParent()
      apply_attribute(node, proto.attribute)
    case ast.Subscript():
      proto.subscript.SetInParent()
      apply_subscript(node, proto.subscript)
    case ast.Starred():
      proto.starred.SetInParent()
      apply_starred(node, proto.starred)
    case ast.Name():
      proto.name.SetInParent()
      apply_name(node, proto.name)
    case ast.List():
      proto.list_expr.SetInParent()
      apply_list_expr(node, proto.list_expr)
    case ast.Tuple():
      proto.tuple.SetInParent()
      apply_tuple(node, proto.tuple)
    case ast.Slice():
      proto.slice.SetInParent()
      apply_slice(node, proto.slice)

def apply_bool_op(node: ast.BoolOp, proto: python3_10_pb2.BoolOp):
  apply_abstract_boolop(node.op, proto.op)
  for x in node.values:
    v = proto.values.add()
    apply_abstract_expr(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_named_expr(node: ast.NamedExpr, proto: python3_10_pb2.NamedExpr):
  apply_abstract_expr(node.target, proto.target)
  apply_abstract_expr(node.value, proto.value)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_bin_op(node: ast.BinOp, proto: python3_10_pb2.BinOp):
  apply_abstract_expr(node.left, proto.left)
  apply_abstract_operator(node.op, proto.op)
  apply_abstract_expr(node.right, proto.right)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_unary_op(node: ast.UnaryOp, proto: python3_10_pb2.UnaryOp):
  apply_abstract_unaryop(node.op, proto.op)
  apply_abstract_expr(node.operand, proto.operand)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_lambda(node: ast.Lambda, proto: python3_10_pb2.Lambda):
  apply_arguments(node.args, proto.args)
  apply_abstract_expr(node.body, proto.body)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_if_exp(node: ast.IfExp, proto: python3_10_pb2.IfExp):
  apply_abstract_expr(node.test, proto.test)
  apply_abstract_expr(node.body, proto.body)
  apply_abstract_expr(node.orelse, proto.orelse)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_dict(node: ast.Dict, proto: python3_10_pb2.Dict):
  for x in node.keys:
    v = proto.keys.add()
    apply_abstract_expr(x, v)
  for x in node.values:
    v = proto.values.add()
    apply_abstract_expr(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_set_expr(node: ast.Set, proto: python3_10_pb2.SetExpr):
  for x in node.elts:
    v = proto.elts.add()
    apply_abstract_expr(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_list_comp(node: ast.ListComp, proto: python3_10_pb2.ListComp):
  apply_abstract_expr(node.elt, proto.elt)
  for x in node.generators:
    v = proto.generators.add()
    apply_comprehension(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_set_comp(node: ast.SetComp, proto: python3_10_pb2.SetComp):
  apply_abstract_expr(node.elt, proto.elt)
  for x in node.generators:
    v = proto.generators.add()
    apply_comprehension(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_dict_comp(node: ast.DictComp, proto: python3_10_pb2.DictComp):
  apply_abstract_expr(node.key, proto.key)
  apply_abstract_expr(node.value, proto.value)
  for x in node.generators:
    v = proto.generators.add()
    apply_comprehension(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_generator_exp(node: ast.GeneratorExp, proto: python3_10_pb2.GeneratorExp):
  apply_abstract_expr(node.elt, proto.elt)
  for x in node.generators:
    v = proto.generators.add()
    apply_comprehension(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_await(node: ast.Await, proto: python3_10_pb2.Await):
  apply_abstract_expr(node.value, proto.value)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_yield(node: ast.Yield, proto: python3_10_pb2.Yield):
  if node.value is not None:
    apply_abstract_expr(node.value, proto.value)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_yield_from(node: ast.YieldFrom, proto: python3_10_pb2.YieldFrom):
  apply_abstract_expr(node.value, proto.value)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_compare(node: ast.Compare, proto: python3_10_pb2.Compare):
  apply_abstract_expr(node.left, proto.left)
  for x in node.ops:
    v = proto.ops.add()
    apply_abstract_cmpop(x, v)
  for x in node.comparators:
    v = proto.comparators.add()
    apply_abstract_expr(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_call(node: ast.Call, proto: python3_10_pb2.Call):
  apply_abstract_expr(node.func, proto.func)
  for x in node.args:
    v = proto.args.add()
    apply_abstract_expr(x, v)
  for x in node.keywords:
    v = proto.keywords.add()
    apply_keyword(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_formatted_value(node: ast.FormattedValue, proto: python3_10_pb2.FormattedValue):
  apply_abstract_expr(node.value, proto.value)
  proto.conversion = node.conversion
  if node.format_spec is not None:
    apply_abstract_expr(node.format_spec, proto.format_spec)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_joined_str(node: ast.JoinedStr, proto: python3_10_pb2.JoinedStr):
  for x in node.values:
    v = proto.values.add()
    apply_abstract_expr(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_constant(node: ast.Constant, proto: python3_10_pb2.Constant):
  proto.value = f"{type(node.value)}{str(node.value)}"
  if node.kind is not None:
    proto.kind = node.kind
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_attribute(node: ast.Attribute, proto: python3_10_pb2.Attribute):
  apply_abstract_expr(node.value, proto.value)
  proto.attr = node.attr
  apply_abstract_expr_context(node.ctx, proto.ctx)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_subscript(node: ast.Subscript, proto: python3_10_pb2.Subscript):
  apply_abstract_expr(node.value, proto.value)
  apply_abstract_expr(node.slice, proto.slice)
  apply_abstract_expr_context(node.ctx, proto.ctx)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_starred(node: ast.Starred, proto: python3_10_pb2.Starred):
  apply_abstract_expr(node.value, proto.value)
  apply_abstract_expr_context(node.ctx, proto.ctx)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_name(node: ast.Name, proto: python3_10_pb2.Name):
  proto.id = node.id
  apply_abstract_expr_context(node.ctx, proto.ctx)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_list_expr(node: ast.List, proto: python3_10_pb2.ListExpr):
  for x in node.elts:
    v = proto.elts.add()
    apply_abstract_expr(x, v)
  apply_abstract_expr_context(node.ctx, proto.ctx)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_tuple(node: ast.Tuple, proto: python3_10_pb2.Tuple):
  for x in node.elts:
    v = proto.elts.add()
    apply_abstract_expr(x, v)
  apply_abstract_expr_context(node.ctx, proto.ctx)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_slice(node: ast.Slice, proto: python3_10_pb2.Slice):
  if node.lower is not None:
    apply_abstract_expr(node.lower, proto.lower)
  if node.upper is not None:
    apply_abstract_expr(node.upper, proto.upper)
  if node.step is not None:
    apply_abstract_expr(node.step, proto.step)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_abstract_expr_context(node: ast.expr_context, proto: python3_10_pb2.AbstractExprContext):
  match node:
    case ast.Load():
      proto.load.SetInParent()
      apply_load(node, proto.load)
    case ast.Store():
      proto.store.SetInParent()
      apply_store(node, proto.store)
    case ast.Del():
      getattr(proto, "del").SetInParent()
      apply_del(node, getattr(proto, "del"))

def apply_load(node: ast.Load, proto: python3_10_pb2.Load):
  pass
def apply_store(node: ast.Store, proto: python3_10_pb2.Store):
  pass
def apply_del(node: ast.Del, proto: python3_10_pb2.Del):
  pass
def apply_abstract_boolop(node: ast.boolop, proto: python3_10_pb2.AbstractBoolop):
  match node:
    case ast.And():
      getattr(proto, "and").SetInParent()
      apply_and(node, getattr(proto, "and"))
    case ast.Or():
      getattr(proto, "or").SetInParent()
      apply_or(node, getattr(proto, "or"))

def apply_and(node: ast.And, proto: python3_10_pb2.And):
  pass
def apply_or(node: ast.Or, proto: python3_10_pb2.Or):
  pass
def apply_abstract_operator(node: ast.operator, proto: python3_10_pb2.AbstractOperator):
  match node:
    case ast.Add():
      proto.add.SetInParent()
      apply_add(node, proto.add)
    case ast.Sub():
      proto.sub.SetInParent()
      apply_sub(node, proto.sub)
    case ast.Mult():
      proto.mult.SetInParent()
      apply_mult(node, proto.mult)
    case ast.MatMult():
      proto.mat_mult.SetInParent()
      apply_mat_mult(node, proto.mat_mult)
    case ast.Div():
      proto.div.SetInParent()
      apply_div(node, proto.div)
    case ast.Mod():
      proto.mod.SetInParent()
      apply_mod(node, proto.mod)
    case ast.Pow():
      proto.pow.SetInParent()
      apply_pow(node, proto.pow)
    case ast.LShift():
      proto.l_shift.SetInParent()
      apply_l_shift(node, proto.l_shift)
    case ast.RShift():
      proto.r_shift.SetInParent()
      apply_r_shift(node, proto.r_shift)
    case ast.BitOr():
      proto.bit_or.SetInParent()
      apply_bit_or(node, proto.bit_or)
    case ast.BitXor():
      proto.bit_xor.SetInParent()
      apply_bit_xor(node, proto.bit_xor)
    case ast.BitAnd():
      proto.bit_and.SetInParent()
      apply_bit_and(node, proto.bit_and)
    case ast.FloorDiv():
      proto.floor_div.SetInParent()
      apply_floor_div(node, proto.floor_div)

def apply_add(node: ast.Add, proto: python3_10_pb2.Add):
  pass
def apply_sub(node: ast.Sub, proto: python3_10_pb2.Sub):
  pass
def apply_mult(node: ast.Mult, proto: python3_10_pb2.Mult):
  pass
def apply_mat_mult(node: ast.MatMult, proto: python3_10_pb2.MatMult):
  pass
def apply_div(node: ast.Div, proto: python3_10_pb2.Div):
  pass
def apply_mod(node: ast.Mod, proto: python3_10_pb2.Mod):
  pass
def apply_pow(node: ast.Pow, proto: python3_10_pb2.Pow):
  pass
def apply_l_shift(node: ast.LShift, proto: python3_10_pb2.LShift):
  pass
def apply_r_shift(node: ast.RShift, proto: python3_10_pb2.RShift):
  pass
def apply_bit_or(node: ast.BitOr, proto: python3_10_pb2.BitOr):
  pass
def apply_bit_xor(node: ast.BitXor, proto: python3_10_pb2.BitXor):
  pass
def apply_bit_and(node: ast.BitAnd, proto: python3_10_pb2.BitAnd):
  pass
def apply_floor_div(node: ast.FloorDiv, proto: python3_10_pb2.FloorDiv):
  pass
def apply_abstract_unaryop(node: ast.unaryop, proto: python3_10_pb2.AbstractUnaryop):
  match node:
    case ast.Invert():
      proto.invert.SetInParent()
      apply_invert(node, proto.invert)
    case ast.Not():
      getattr(proto, "not").SetInParent()
      apply_not(node, getattr(proto, "not"))
    case ast.UAdd():
      proto.u_add.SetInParent()
      apply_u_add(node, proto.u_add)
    case ast.USub():
      proto.u_sub.SetInParent()
      apply_u_sub(node, proto.u_sub)

def apply_invert(node: ast.Invert, proto: python3_10_pb2.Invert):
  pass
def apply_not(node: ast.Not, proto: python3_10_pb2.Not):
  pass
def apply_u_add(node: ast.UAdd, proto: python3_10_pb2.UAdd):
  pass
def apply_u_sub(node: ast.USub, proto: python3_10_pb2.USub):
  pass
def apply_abstract_cmpop(node: ast.cmpop, proto: python3_10_pb2.AbstractCmpop):
  match node:
    case ast.Eq():
      proto.eq.SetInParent()
      apply_eq(node, proto.eq)
    case ast.NotEq():
      proto.not_eq.SetInParent()
      apply_not_eq(node, proto.not_eq)
    case ast.Lt():
      proto.lt.SetInParent()
      apply_lt(node, proto.lt)
    case ast.LtE():
      proto.lt_e.SetInParent()
      apply_lt_e(node, proto.lt_e)
    case ast.Gt():
      proto.gt.SetInParent()
      apply_gt(node, proto.gt)
    case ast.GtE():
      proto.gt_e.SetInParent()
      apply_gt_e(node, proto.gt_e)
    case ast.Is():
      getattr(proto, "is").SetInParent()
      apply_is(node, getattr(proto, "is"))
    case ast.IsNot():
      proto.is_not.SetInParent()
      apply_is_not(node, proto.is_not)
    case ast.In():
      getattr(proto, "in").SetInParent()
      apply_in(node, getattr(proto, "in"))
    case ast.NotIn():
      proto.not_in.SetInParent()
      apply_not_in(node, proto.not_in)

def apply_eq(node: ast.Eq, proto: python3_10_pb2.Eq):
  pass
def apply_not_eq(node: ast.NotEq, proto: python3_10_pb2.NotEq):
  pass
def apply_lt(node: ast.Lt, proto: python3_10_pb2.Lt):
  pass
def apply_lt_e(node: ast.LtE, proto: python3_10_pb2.LtE):
  pass
def apply_gt(node: ast.Gt, proto: python3_10_pb2.Gt):
  pass
def apply_gt_e(node: ast.GtE, proto: python3_10_pb2.GtE):
  pass
def apply_is(node: ast.Is, proto: python3_10_pb2.Is):
  pass
def apply_is_not(node: ast.IsNot, proto: python3_10_pb2.IsNot):
  pass
def apply_in(node: ast.In, proto: python3_10_pb2.In):
  pass
def apply_not_in(node: ast.NotIn, proto: python3_10_pb2.NotIn):
  pass
def apply_comprehension(node: ast.comprehension, proto: python3_10_pb2.Comprehension):
  apply_abstract_expr(node.target, proto.target)
  apply_abstract_expr(node.iter, proto.iter)
  for x in node.ifs:
    v = proto.ifs.add()
    apply_abstract_expr(x, v)
  proto.is_async = node.is_async

def apply_abstract_excepthandler(node: ast.excepthandler, proto: python3_10_pb2.AbstractExcepthandler):
  match node:
    case ast.ExceptHandler():
      proto.except_handler.SetInParent()
      apply_except_handler(node, proto.except_handler)

def apply_except_handler(node: ast.ExceptHandler, proto: python3_10_pb2.ExceptHandler):
  if node.type is not None:
    apply_abstract_expr(node.type, proto.type)
  if node.name is not None:
    proto.name = node.name
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_arguments(node: ast.arguments, proto: python3_10_pb2.Arguments):
  for x in node.posonlyargs:
    v = proto.posonlyargs.add()
    apply_arg(x, v)
  for x in node.args:
    v = proto.args.add()
    apply_arg(x, v)
  if node.vararg is not None:
    apply_arg(node.vararg, proto.vararg)
  for x in node.kwonlyargs:
    v = proto.kwonlyargs.add()
    apply_arg(x, v)
  for x in node.kw_defaults:
    v = proto.kw_defaults.add()
    apply_abstract_expr(x, v)
  if node.kwarg is not None:
    apply_arg(node.kwarg, proto.kwarg)
  for x in node.defaults:
    v = proto.defaults.add()
    apply_abstract_expr(x, v)

def apply_arg(node: ast.arg, proto: python3_10_pb2.Arg):
  proto.arg = node.arg
  if node.annotation is not None:
    apply_abstract_expr(node.annotation, proto.annotation)
  if node.type_comment is not None:
    proto.type_comment = node.type_comment
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_keyword(node: ast.keyword, proto: python3_10_pb2.Keyword):
  if node.arg is not None:
    proto.arg = node.arg
  apply_abstract_expr(node.value, proto.value)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_alias(node: ast.alias, proto: python3_10_pb2.Alias):
  proto.name = node.name
  if node.asname is not None:
    proto.asname = node.asname
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  if node.end_lineno is not None:
    proto.end_lineno = node.end_lineno
  if node.end_col_offset is not None:
    proto.end_col_offset = node.end_col_offset

def apply_withitem(node: ast.withitem, proto: python3_10_pb2.Withitem):
  apply_abstract_expr(node.context_expr, proto.context_expr)
  if node.optional_vars is not None:
    apply_abstract_expr(node.optional_vars, proto.optional_vars)

def apply_match_case(node: ast.match_case, proto: python3_10_pb2.MatchCase):
  apply_abstract_pattern(node.pattern, proto.pattern)
  if node.guard is not None:
    apply_abstract_expr(node.guard, proto.guard)
  for x in node.body:
    v = proto.body.add()
    apply_abstract_stmt(x, v)

def apply_abstract_pattern(node: ast.pattern, proto: python3_10_pb2.AbstractPattern):
  match node:
    case ast.MatchValue():
      proto.match_value.SetInParent()
      apply_match_value(node, proto.match_value)
    case ast.MatchSingleton():
      proto.match_singleton.SetInParent()
      apply_match_singleton(node, proto.match_singleton)
    case ast.MatchSequence():
      proto.match_sequence.SetInParent()
      apply_match_sequence(node, proto.match_sequence)
    case ast.MatchMapping():
      proto.match_mapping.SetInParent()
      apply_match_mapping(node, proto.match_mapping)
    case ast.MatchClass():
      proto.match_class.SetInParent()
      apply_match_class(node, proto.match_class)
    case ast.MatchStar():
      proto.match_star.SetInParent()
      apply_match_star(node, proto.match_star)
    case ast.MatchAs():
      proto.match_as.SetInParent()
      apply_match_as(node, proto.match_as)
    case ast.MatchOr():
      proto.match_or.SetInParent()
      apply_match_or(node, proto.match_or)

def apply_match_value(node: ast.MatchValue, proto: python3_10_pb2.MatchValue):
  apply_abstract_expr(node.value, proto.value)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  proto.end_lineno = node.end_lineno
  proto.end_col_offset = node.end_col_offset

def apply_match_singleton(node: ast.MatchSingleton, proto: python3_10_pb2.MatchSingleton):
  proto.value = f"{type(node.value)}{str(node.value)}"
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  proto.end_lineno = node.end_lineno
  proto.end_col_offset = node.end_col_offset

def apply_match_sequence(node: ast.MatchSequence, proto: python3_10_pb2.MatchSequence):
  for x in node.patterns:
    v = proto.patterns.add()
    apply_abstract_pattern(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  proto.end_lineno = node.end_lineno
  proto.end_col_offset = node.end_col_offset

def apply_match_mapping(node: ast.MatchMapping, proto: python3_10_pb2.MatchMapping):
  for x in node.keys:
    v = proto.keys.add()
    apply_abstract_expr(x, v)
  for x in node.patterns:
    v = proto.patterns.add()
    apply_abstract_pattern(x, v)
  if node.rest is not None:
    proto.rest = node.rest
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  proto.end_lineno = node.end_lineno
  proto.end_col_offset = node.end_col_offset

def apply_match_class(node: ast.MatchClass, proto: python3_10_pb2.MatchClass):
  apply_abstract_expr(node.cls, proto.cls)
  for x in node.patterns:
    v = proto.patterns.add()
    apply_abstract_pattern(x, v)
  for x in node.kwd_attrs:
    proto.kwd_attrs.append(x)
  for x in node.kwd_patterns:
    v = proto.kwd_patterns.add()
    apply_abstract_pattern(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  proto.end_lineno = node.end_lineno
  proto.end_col_offset = node.end_col_offset

def apply_match_star(node: ast.MatchStar, proto: python3_10_pb2.MatchStar):
  if node.name is not None:
    proto.name = node.name
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  proto.end_lineno = node.end_lineno
  proto.end_col_offset = node.end_col_offset

def apply_match_as(node: ast.MatchAs, proto: python3_10_pb2.MatchAs):
  if node.pattern is not None:
    apply_abstract_pattern(node.pattern, proto.pattern)
  if node.name is not None:
    proto.name = node.name
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  proto.end_lineno = node.end_lineno
  proto.end_col_offset = node.end_col_offset

def apply_match_or(node: ast.MatchOr, proto: python3_10_pb2.MatchOr):
  for x in node.patterns:
    v = proto.patterns.add()
    apply_abstract_pattern(x, v)
  proto.lineno = node.lineno
  proto.col_offset = node.col_offset
  proto.end_lineno = node.end_lineno
  proto.end_col_offset = node.end_col_offset

def apply_abstract_type_ignore(node: ast.type_ignore, proto: python3_10_pb2.AbstractTypeIgnore):
  match node:
    case ast.TypeIgnore():
      proto.type_ignore.SetInParent()
      apply_type_ignore(node, proto.type_ignore)

def apply_type_ignore(node: ast.TypeIgnore, proto: python3_10_pb2.TypeIgnore):
  proto.lineno = node.lineno
  proto.tag = node.tag


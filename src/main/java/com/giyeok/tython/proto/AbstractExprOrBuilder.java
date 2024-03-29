// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: python3_10.proto

package com.giyeok.tython.proto;

public interface AbstractExprOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.giyeok.tython.proto.AbstractExpr)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.com.giyeok.tython.proto.BoolOp bool_op = 1;</code>
   * @return Whether the boolOp field is set.
   */
  boolean hasBoolOp();
  /**
   * <code>.com.giyeok.tython.proto.BoolOp bool_op = 1;</code>
   * @return The boolOp.
   */
  com.giyeok.tython.proto.BoolOp getBoolOp();
  /**
   * <code>.com.giyeok.tython.proto.BoolOp bool_op = 1;</code>
   */
  com.giyeok.tython.proto.BoolOpOrBuilder getBoolOpOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.NamedExpr named_expr = 2;</code>
   * @return Whether the namedExpr field is set.
   */
  boolean hasNamedExpr();
  /**
   * <code>.com.giyeok.tython.proto.NamedExpr named_expr = 2;</code>
   * @return The namedExpr.
   */
  com.giyeok.tython.proto.NamedExpr getNamedExpr();
  /**
   * <code>.com.giyeok.tython.proto.NamedExpr named_expr = 2;</code>
   */
  com.giyeok.tython.proto.NamedExprOrBuilder getNamedExprOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.BinOp bin_op = 3;</code>
   * @return Whether the binOp field is set.
   */
  boolean hasBinOp();
  /**
   * <code>.com.giyeok.tython.proto.BinOp bin_op = 3;</code>
   * @return The binOp.
   */
  com.giyeok.tython.proto.BinOp getBinOp();
  /**
   * <code>.com.giyeok.tython.proto.BinOp bin_op = 3;</code>
   */
  com.giyeok.tython.proto.BinOpOrBuilder getBinOpOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.UnaryOp unary_op = 4;</code>
   * @return Whether the unaryOp field is set.
   */
  boolean hasUnaryOp();
  /**
   * <code>.com.giyeok.tython.proto.UnaryOp unary_op = 4;</code>
   * @return The unaryOp.
   */
  com.giyeok.tython.proto.UnaryOp getUnaryOp();
  /**
   * <code>.com.giyeok.tython.proto.UnaryOp unary_op = 4;</code>
   */
  com.giyeok.tython.proto.UnaryOpOrBuilder getUnaryOpOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Lambda lambda = 5;</code>
   * @return Whether the lambda field is set.
   */
  boolean hasLambda();
  /**
   * <code>.com.giyeok.tython.proto.Lambda lambda = 5;</code>
   * @return The lambda.
   */
  com.giyeok.tython.proto.Lambda getLambda();
  /**
   * <code>.com.giyeok.tython.proto.Lambda lambda = 5;</code>
   */
  com.giyeok.tython.proto.LambdaOrBuilder getLambdaOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.IfExp if_exp = 6;</code>
   * @return Whether the ifExp field is set.
   */
  boolean hasIfExp();
  /**
   * <code>.com.giyeok.tython.proto.IfExp if_exp = 6;</code>
   * @return The ifExp.
   */
  com.giyeok.tython.proto.IfExp getIfExp();
  /**
   * <code>.com.giyeok.tython.proto.IfExp if_exp = 6;</code>
   */
  com.giyeok.tython.proto.IfExpOrBuilder getIfExpOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Dict dict = 7;</code>
   * @return Whether the dict field is set.
   */
  boolean hasDict();
  /**
   * <code>.com.giyeok.tython.proto.Dict dict = 7;</code>
   * @return The dict.
   */
  com.giyeok.tython.proto.Dict getDict();
  /**
   * <code>.com.giyeok.tython.proto.Dict dict = 7;</code>
   */
  com.giyeok.tython.proto.DictOrBuilder getDictOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.SetExpr set_expr = 8;</code>
   * @return Whether the setExpr field is set.
   */
  boolean hasSetExpr();
  /**
   * <code>.com.giyeok.tython.proto.SetExpr set_expr = 8;</code>
   * @return The setExpr.
   */
  com.giyeok.tython.proto.SetExpr getSetExpr();
  /**
   * <code>.com.giyeok.tython.proto.SetExpr set_expr = 8;</code>
   */
  com.giyeok.tython.proto.SetExprOrBuilder getSetExprOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.ListComp list_comp = 9;</code>
   * @return Whether the listComp field is set.
   */
  boolean hasListComp();
  /**
   * <code>.com.giyeok.tython.proto.ListComp list_comp = 9;</code>
   * @return The listComp.
   */
  com.giyeok.tython.proto.ListComp getListComp();
  /**
   * <code>.com.giyeok.tython.proto.ListComp list_comp = 9;</code>
   */
  com.giyeok.tython.proto.ListCompOrBuilder getListCompOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.SetComp set_comp = 10;</code>
   * @return Whether the setComp field is set.
   */
  boolean hasSetComp();
  /**
   * <code>.com.giyeok.tython.proto.SetComp set_comp = 10;</code>
   * @return The setComp.
   */
  com.giyeok.tython.proto.SetComp getSetComp();
  /**
   * <code>.com.giyeok.tython.proto.SetComp set_comp = 10;</code>
   */
  com.giyeok.tython.proto.SetCompOrBuilder getSetCompOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.DictComp dict_comp = 11;</code>
   * @return Whether the dictComp field is set.
   */
  boolean hasDictComp();
  /**
   * <code>.com.giyeok.tython.proto.DictComp dict_comp = 11;</code>
   * @return The dictComp.
   */
  com.giyeok.tython.proto.DictComp getDictComp();
  /**
   * <code>.com.giyeok.tython.proto.DictComp dict_comp = 11;</code>
   */
  com.giyeok.tython.proto.DictCompOrBuilder getDictCompOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.GeneratorExp generator_exp = 12;</code>
   * @return Whether the generatorExp field is set.
   */
  boolean hasGeneratorExp();
  /**
   * <code>.com.giyeok.tython.proto.GeneratorExp generator_exp = 12;</code>
   * @return The generatorExp.
   */
  com.giyeok.tython.proto.GeneratorExp getGeneratorExp();
  /**
   * <code>.com.giyeok.tython.proto.GeneratorExp generator_exp = 12;</code>
   */
  com.giyeok.tython.proto.GeneratorExpOrBuilder getGeneratorExpOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Await await = 13;</code>
   * @return Whether the await field is set.
   */
  boolean hasAwait();
  /**
   * <code>.com.giyeok.tython.proto.Await await = 13;</code>
   * @return The await.
   */
  com.giyeok.tython.proto.Await getAwait();
  /**
   * <code>.com.giyeok.tython.proto.Await await = 13;</code>
   */
  com.giyeok.tython.proto.AwaitOrBuilder getAwaitOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Yield yield = 14;</code>
   * @return Whether the yield field is set.
   */
  boolean hasYield();
  /**
   * <code>.com.giyeok.tython.proto.Yield yield = 14;</code>
   * @return The yield.
   */
  com.giyeok.tython.proto.Yield getYield();
  /**
   * <code>.com.giyeok.tython.proto.Yield yield = 14;</code>
   */
  com.giyeok.tython.proto.YieldOrBuilder getYieldOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.YieldFrom yield_from = 15;</code>
   * @return Whether the yieldFrom field is set.
   */
  boolean hasYieldFrom();
  /**
   * <code>.com.giyeok.tython.proto.YieldFrom yield_from = 15;</code>
   * @return The yieldFrom.
   */
  com.giyeok.tython.proto.YieldFrom getYieldFrom();
  /**
   * <code>.com.giyeok.tython.proto.YieldFrom yield_from = 15;</code>
   */
  com.giyeok.tython.proto.YieldFromOrBuilder getYieldFromOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Compare compare = 16;</code>
   * @return Whether the compare field is set.
   */
  boolean hasCompare();
  /**
   * <code>.com.giyeok.tython.proto.Compare compare = 16;</code>
   * @return The compare.
   */
  com.giyeok.tython.proto.Compare getCompare();
  /**
   * <code>.com.giyeok.tython.proto.Compare compare = 16;</code>
   */
  com.giyeok.tython.proto.CompareOrBuilder getCompareOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Call call = 17;</code>
   * @return Whether the call field is set.
   */
  boolean hasCall();
  /**
   * <code>.com.giyeok.tython.proto.Call call = 17;</code>
   * @return The call.
   */
  com.giyeok.tython.proto.Call getCall();
  /**
   * <code>.com.giyeok.tython.proto.Call call = 17;</code>
   */
  com.giyeok.tython.proto.CallOrBuilder getCallOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.FormattedValue formatted_value = 18;</code>
   * @return Whether the formattedValue field is set.
   */
  boolean hasFormattedValue();
  /**
   * <code>.com.giyeok.tython.proto.FormattedValue formatted_value = 18;</code>
   * @return The formattedValue.
   */
  com.giyeok.tython.proto.FormattedValue getFormattedValue();
  /**
   * <code>.com.giyeok.tython.proto.FormattedValue formatted_value = 18;</code>
   */
  com.giyeok.tython.proto.FormattedValueOrBuilder getFormattedValueOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.JoinedStr joined_str = 19;</code>
   * @return Whether the joinedStr field is set.
   */
  boolean hasJoinedStr();
  /**
   * <code>.com.giyeok.tython.proto.JoinedStr joined_str = 19;</code>
   * @return The joinedStr.
   */
  com.giyeok.tython.proto.JoinedStr getJoinedStr();
  /**
   * <code>.com.giyeok.tython.proto.JoinedStr joined_str = 19;</code>
   */
  com.giyeok.tython.proto.JoinedStrOrBuilder getJoinedStrOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Constant constant = 20;</code>
   * @return Whether the constant field is set.
   */
  boolean hasConstant();
  /**
   * <code>.com.giyeok.tython.proto.Constant constant = 20;</code>
   * @return The constant.
   */
  com.giyeok.tython.proto.Constant getConstant();
  /**
   * <code>.com.giyeok.tython.proto.Constant constant = 20;</code>
   */
  com.giyeok.tython.proto.ConstantOrBuilder getConstantOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Attribute attribute = 21;</code>
   * @return Whether the attribute field is set.
   */
  boolean hasAttribute();
  /**
   * <code>.com.giyeok.tython.proto.Attribute attribute = 21;</code>
   * @return The attribute.
   */
  com.giyeok.tython.proto.Attribute getAttribute();
  /**
   * <code>.com.giyeok.tython.proto.Attribute attribute = 21;</code>
   */
  com.giyeok.tython.proto.AttributeOrBuilder getAttributeOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Subscript subscript = 22;</code>
   * @return Whether the subscript field is set.
   */
  boolean hasSubscript();
  /**
   * <code>.com.giyeok.tython.proto.Subscript subscript = 22;</code>
   * @return The subscript.
   */
  com.giyeok.tython.proto.Subscript getSubscript();
  /**
   * <code>.com.giyeok.tython.proto.Subscript subscript = 22;</code>
   */
  com.giyeok.tython.proto.SubscriptOrBuilder getSubscriptOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Starred starred = 23;</code>
   * @return Whether the starred field is set.
   */
  boolean hasStarred();
  /**
   * <code>.com.giyeok.tython.proto.Starred starred = 23;</code>
   * @return The starred.
   */
  com.giyeok.tython.proto.Starred getStarred();
  /**
   * <code>.com.giyeok.tython.proto.Starred starred = 23;</code>
   */
  com.giyeok.tython.proto.StarredOrBuilder getStarredOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Name name = 24;</code>
   * @return Whether the name field is set.
   */
  boolean hasName();
  /**
   * <code>.com.giyeok.tython.proto.Name name = 24;</code>
   * @return The name.
   */
  com.giyeok.tython.proto.Name getName();
  /**
   * <code>.com.giyeok.tython.proto.Name name = 24;</code>
   */
  com.giyeok.tython.proto.NameOrBuilder getNameOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.ListExpr list_expr = 25;</code>
   * @return Whether the listExpr field is set.
   */
  boolean hasListExpr();
  /**
   * <code>.com.giyeok.tython.proto.ListExpr list_expr = 25;</code>
   * @return The listExpr.
   */
  com.giyeok.tython.proto.ListExpr getListExpr();
  /**
   * <code>.com.giyeok.tython.proto.ListExpr list_expr = 25;</code>
   */
  com.giyeok.tython.proto.ListExprOrBuilder getListExprOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Tuple tuple = 26;</code>
   * @return Whether the tuple field is set.
   */
  boolean hasTuple();
  /**
   * <code>.com.giyeok.tython.proto.Tuple tuple = 26;</code>
   * @return The tuple.
   */
  com.giyeok.tython.proto.Tuple getTuple();
  /**
   * <code>.com.giyeok.tython.proto.Tuple tuple = 26;</code>
   */
  com.giyeok.tython.proto.TupleOrBuilder getTupleOrBuilder();

  /**
   * <code>.com.giyeok.tython.proto.Slice slice = 27;</code>
   * @return Whether the slice field is set.
   */
  boolean hasSlice();
  /**
   * <code>.com.giyeok.tython.proto.Slice slice = 27;</code>
   * @return The slice.
   */
  com.giyeok.tython.proto.Slice getSlice();
  /**
   * <code>.com.giyeok.tython.proto.Slice slice = 27;</code>
   */
  com.giyeok.tython.proto.SliceOrBuilder getSliceOrBuilder();

  public com.giyeok.tython.proto.AbstractExpr.AbstractExprCase getAbstractExprCase();
}

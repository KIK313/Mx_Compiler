	.text
	.file	"builtin.c"
	.globl	print                           # -- Begin function print
	.p2align	4, 0x90
	.type	print,@function
print:                                  # @print
.Lprint$local:
# %bb.0:
	pushl	%ebx
	subl	$8, %esp
	calll	.L0$pb
.L0$pb:
	popl	%ebx
.Ltmp0:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp0-.L0$pb), %ebx
	movl	16(%esp), %ecx
	movl	%esp, %eax
	movl	%ecx, 4(%eax)
	leal	.L.str@GOTOFF(%ebx), %ecx
	movl	%ecx, (%eax)
	calll	printf
	addl	$8, %esp
	popl	%ebx
	retl
.Lfunc_end0:
	.size	print, .Lfunc_end0-print
                                        # -- End function
	.globl	println                         # -- Begin function println
	.p2align	4, 0x90
	.type	println,@function
println:                                # @println
.Lprintln$local:
# %bb.0:
	pushl	%ebx
	subl	$8, %esp
	calll	.L1$pb
.L1$pb:
	popl	%ebx
.Ltmp1:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp1-.L1$pb), %ebx
	movl	16(%esp), %ecx
	movl	%esp, %eax
	movl	%ecx, 4(%eax)
	leal	.L.str.1@GOTOFF(%ebx), %ecx
	movl	%ecx, (%eax)
	calll	printf
	addl	$8, %esp
	popl	%ebx
	retl
.Lfunc_end1:
	.size	println, .Lfunc_end1-println
                                        # -- End function
	.globl	printInt                        # -- Begin function printInt
	.p2align	4, 0x90
	.type	printInt,@function
printInt:                               # @printInt
.LprintInt$local:
# %bb.0:
	pushl	%ebx
	subl	$8, %esp
	calll	.L2$pb
.L2$pb:
	popl	%ebx
.Ltmp2:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp2-.L2$pb), %ebx
	movl	16(%esp), %ecx
	movl	%esp, %eax
	movl	%ecx, 4(%eax)
	leal	.L.str.2@GOTOFF(%ebx), %ecx
	movl	%ecx, (%eax)
	calll	printf
	addl	$8, %esp
	popl	%ebx
	retl
.Lfunc_end2:
	.size	printInt, .Lfunc_end2-printInt
                                        # -- End function
	.globl	printlnInt                      # -- Begin function printlnInt
	.p2align	4, 0x90
	.type	printlnInt,@function
printlnInt:                             # @printlnInt
.LprintlnInt$local:
# %bb.0:
	pushl	%ebx
	subl	$8, %esp
	calll	.L3$pb
.L3$pb:
	popl	%ebx
.Ltmp3:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp3-.L3$pb), %ebx
	movl	16(%esp), %ecx
	movl	%esp, %eax
	movl	%ecx, 4(%eax)
	leal	.L.str.3@GOTOFF(%ebx), %ecx
	movl	%ecx, (%eax)
	calll	printf
	addl	$8, %esp
	popl	%ebx
	retl
.Lfunc_end3:
	.size	printlnInt, .Lfunc_end3-printlnInt
                                        # -- End function
	.globl	getString                       # -- Begin function getString
	.p2align	4, 0x90
	.type	getString,@function
getString:                              # @getString
.LgetString$local:
# %bb.0:
	pushl	%ebx
	subl	$24, %esp
	calll	.L4$pb
.L4$pb:
	popl	%ebx
.Ltmp4:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp4-.L4$pb), %ebx
	movl	%ebx, 16(%esp)                  # 4-byte Spill
	movl	$256, (%esp)                    # imm = 0x100
	calll	malloc
	movl	16(%esp), %ebx                  # 4-byte Reload
	movl	%eax, 20(%esp)                  # 4-byte Spill
	leal	.L.str@GOTOFF(%ebx), %ecx
	movl	%ecx, (%esp)
	movl	%eax, 4(%esp)
	calll	scanf
                                        # kill: def $ecx killed $eax
	movl	20(%esp), %eax                  # 4-byte Reload
	addl	$24, %esp
	popl	%ebx
	retl
.Lfunc_end4:
	.size	getString, .Lfunc_end4-getString
                                        # -- End function
	.globl	getInt                          # -- Begin function getInt
	.p2align	4, 0x90
	.type	getInt,@function
getInt:                                 # @getInt
.LgetInt$local:
# %bb.0:
	pushl	%ebx
	subl	$24, %esp
	calll	.L5$pb
.L5$pb:
	popl	%ebx
.Ltmp5:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp5-.L5$pb), %ebx
	leal	.L.str.2@GOTOFF(%ebx), %ecx
	leal	20(%esp), %eax
	movl	%ecx, (%esp)
	movl	%eax, 4(%esp)
	calll	scanf
	movl	20(%esp), %eax
	addl	$24, %esp
	popl	%ebx
	retl
.Lfunc_end5:
	.size	getInt, .Lfunc_end5-getInt
                                        # -- End function
	.globl	toString                        # -- Begin function toString
	.p2align	4, 0x90
	.type	toString,@function
toString:                               # @toString
.LtoString$local:
# %bb.0:
	pushl	%ebx
	subl	$24, %esp
	calll	.L6$pb
.L6$pb:
	popl	%ebx
.Ltmp6:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp6-.L6$pb), %ebx
	movl	%ebx, 12(%esp)                  # 4-byte Spill
	movl	32(%esp), %eax
	movl	%eax, 16(%esp)                  # 4-byte Spill
	movl	$16, (%esp)
	calll	malloc
	movl	12(%esp), %ebx                  # 4-byte Reload
	movl	%eax, %edx
	movl	16(%esp), %eax                  # 4-byte Reload
	movl	%edx, 20(%esp)                  # 4-byte Spill
	leal	.L.str.2@GOTOFF(%ebx), %ecx
	movl	%edx, (%esp)
	movl	%ecx, 4(%esp)
	movl	%eax, 8(%esp)
	calll	sprintf
                                        # kill: def $ecx killed $eax
	movl	20(%esp), %eax                  # 4-byte Reload
	addl	$24, %esp
	popl	%ebx
	retl
.Lfunc_end6:
	.size	toString, .Lfunc_end6-toString
                                        # -- End function
	.globl	string.length                   # -- Begin function string.length
	.p2align	4, 0x90
	.type	string.length,@function
string.length:                          # @string.length
.Lstring.length$local:
# %bb.0:
	pushl	%ebx
	subl	$8, %esp
	calll	.L7$pb
.L7$pb:
	popl	%ebx
.Ltmp7:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp7-.L7$pb), %ebx
	movl	16(%esp), %ecx
	movl	%esp, %eax
	movl	%ecx, (%eax)
	calll	strlen
	addl	$8, %esp
	popl	%ebx
	retl
.Lfunc_end7:
	.size	string.length, .Lfunc_end7-string.length
                                        # -- End function
	.globl	string.substring                # -- Begin function string.substring
	.p2align	4, 0x90
	.type	string.substring,@function
string.substring:                       # @string.substring
.Lstring.substring$local:
# %bb.0:
	pushl	%ebx
	pushl	%edi
	pushl	%esi
	subl	$32, %esp
	calll	.L8$pb
.L8$pb:
	popl	%ebx
.Ltmp8:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp8-.L8$pb), %ebx
	movl	56(%esp), %eax
	movl	%eax, 16(%esp)                  # 4-byte Spill
	movl	52(%esp), %ecx
	movl	%ecx, 20(%esp)                  # 4-byte Spill
	movl	48(%esp), %edx
	movl	%edx, 8(%esp)                   # 4-byte Spill
	subl	%ecx, %eax
	movl	%eax, 12(%esp)                  # 4-byte Spill
	addl	$1, %eax
	movl	%eax, (%esp)
	calll	malloc
	movl	16(%esp), %ecx                  # 4-byte Reload
	movl	%eax, %edx
	movl	20(%esp), %eax                  # 4-byte Reload
	movl	%edx, 24(%esp)                  # 4-byte Spill
	cmpl	%eax, %ecx
	movl	%eax, 28(%esp)                  # 4-byte Spill
	jg	.LBB8_2
.LBB8_1:
	movl	24(%esp), %eax                  # 4-byte Reload
	movl	12(%esp), %ecx                  # 4-byte Reload
	movb	$0, (%eax,%ecx)
	addl	$32, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	retl
.LBB8_2:                                # =>This Inner Loop Header: Depth=1
	movl	28(%esp), %eax                  # 4-byte Reload
	movl	16(%esp), %ecx                  # 4-byte Reload
	movl	24(%esp), %edx                  # 4-byte Reload
	movl	20(%esp), %edi                  # 4-byte Reload
	movl	8(%esp), %esi                   # 4-byte Reload
	movb	(%esi,%eax), %bl
	movl	%eax, %esi
	subl	%edi, %esi
	movb	%bl, (%edx,%esi)
	addl	$1, %eax
	cmpl	%ecx, %eax
	movl	%eax, 28(%esp)                  # 4-byte Spill
	je	.LBB8_1
	jmp	.LBB8_2
.Lfunc_end8:
	.size	string.substring, .Lfunc_end8-string.substring
                                        # -- End function
	.globl	string.parseInt                 # -- Begin function string.parseInt
	.p2align	4, 0x90
	.type	string.parseInt,@function
string.parseInt:                        # @string.parseInt
.Lstring.parseInt$local:
# %bb.0:
	pushl	%ebx
	pushl	%esi
	subl	$52, %esp
	calll	.L9$pb
.L9$pb:
	popl	%ebx
.Ltmp9:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp9-.L9$pb), %ebx
	movl	64(%esp), %ecx
	movl	%ecx, 28(%esp)                  # 4-byte Spill
	cmpb	$45, (%ecx)
	sete	%al
	andb	$1, %al
	movzbl	%al, %eax
	movl	%eax, 32(%esp)                  # 4-byte Spill
	movl	%esp, %eax
	movl	%ecx, (%eax)
	calll	strlen
	movl	32(%esp), %ecx                  # 4-byte Reload
	movl	%eax, %edx
	movl	%edx, 36(%esp)                  # 4-byte Spill
	xorl	%eax, %eax
	cmpl	%ecx, %edx
	movl	%ecx, 40(%esp)                  # 4-byte Spill
	movl	%eax, %ecx
	movl	%ecx, 44(%esp)                  # 4-byte Spill
	movl	%eax, 48(%esp)                  # 4-byte Spill
	jle	.LBB9_3
.LBB9_1:                                # =>This Inner Loop Header: Depth=1
	movl	28(%esp), %ecx                  # 4-byte Reload
	movl	40(%esp), %edx                  # 4-byte Reload
	movl	44(%esp), %eax                  # 4-byte Reload
	movl	%eax, 16(%esp)                  # 4-byte Spill
	movl	%edx, 20(%esp)                  # 4-byte Spill
	movb	(%ecx,%edx), %cl
	movb	%cl, 27(%esp)                   # 1-byte Spill
	addb	$-48, %cl
	cmpb	$10, %cl
	movl	%eax, 48(%esp)                  # 4-byte Spill
	jae	.LBB9_3
# %bb.2:                                #   in Loop: Header=BB9_1 Depth=1
	movl	36(%esp), %edx                  # 4-byte Reload
	movl	20(%esp), %ecx                  # 4-byte Reload
	movl	16(%esp), %eax                  # 4-byte Reload
	movb	27(%esp), %bl                   # 1-byte Reload
	movzbl	%bl, %esi
	imull	$10, %eax, %eax
	addl	$-48, %eax
	addl	%esi, %eax
	addl	$1, %ecx
	cmpl	%edx, %ecx
	movl	%ecx, 40(%esp)                  # 4-byte Spill
	movl	%eax, %ecx
	movl	%ecx, 44(%esp)                  # 4-byte Spill
	movl	%eax, 48(%esp)                  # 4-byte Spill
	jne	.LBB9_1
.LBB9_3:
	movl	48(%esp), %eax                  # 4-byte Reload
	addl	$52, %esp
	popl	%esi
	popl	%ebx
	retl
.Lfunc_end9:
	.size	string.parseInt, .Lfunc_end9-string.parseInt
                                        # -- End function
	.globl	string.ord                      # -- Begin function string.ord
	.p2align	4, 0x90
	.type	string.ord,@function
string.ord:                             # @string.ord
.Lstring.ord$local:
# %bb.0:
	movl	8(%esp), %ecx
	movl	4(%esp), %eax
	movzbl	(%eax,%ecx), %eax
	retl
.Lfunc_end10:
	.size	string.ord, .Lfunc_end10-string.ord
                                        # -- End function
	.globl	string.add                      # -- Begin function string.add
	.p2align	4, 0x90
	.type	string.add,@function
string.add:                             # @string.add
.Lstring.add$local:
# %bb.0:
	pushl	%ebx
	pushl	%edi
	pushl	%esi
	subl	$48, %esp
	calll	.L11$pb
.L11$pb:
	popl	%ebx
.Ltmp10:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp10-.L11$pb), %ebx
	movl	%ebx, 24(%esp)                  # 4-byte Spill
	movl	68(%esp), %eax
	movl	%eax, 20(%esp)                  # 4-byte Spill
	movl	64(%esp), %ecx
	movl	%ecx, 16(%esp)                  # 4-byte Spill
	movl	%esp, %eax
	movl	%ecx, (%eax)
	calll	strlen
	movl	24(%esp), %ebx                  # 4-byte Reload
	movl	20(%esp), %ecx                  # 4-byte Reload
	movl	%eax, 36(%esp)                  # 4-byte Spill
	movl	%esp, %eax
	movl	%ecx, (%eax)
	calll	strlen
	movl	24(%esp), %ebx                  # 4-byte Reload
	movl	36(%esp), %ecx                  # 4-byte Reload
	movl	%eax, 28(%esp)                  # 4-byte Spill
	addl	%ecx, %eax
	movl	%eax, 32(%esp)                  # 4-byte Spill
	addl	$1, %eax
	movl	%eax, (%esp)
	calll	malloc
	movl	36(%esp), %ecx                  # 4-byte Reload
	movl	%eax, 40(%esp)                  # 4-byte Spill
	xorl	%eax, %eax
	cmpl	$0, %ecx
	movl	%eax, 44(%esp)                  # 4-byte Spill
	jg	.LBB11_2
.LBB11_1:
	movl	28(%esp), %ecx                  # 4-byte Reload
	xorl	%eax, %eax
	cmpl	$0, %ecx
	movl	%eax, 12(%esp)                  # 4-byte Spill
	jg	.LBB11_4
	jmp	.LBB11_3
.LBB11_2:                               # =>This Inner Loop Header: Depth=1
	movl	44(%esp), %eax                  # 4-byte Reload
	movl	36(%esp), %ecx                  # 4-byte Reload
	movl	40(%esp), %edx                  # 4-byte Reload
	movl	16(%esp), %esi                  # 4-byte Reload
	movb	(%esi,%eax), %bl
	movb	%bl, (%edx,%eax)
	addl	$1, %eax
	cmpl	%ecx, %eax
	movl	%eax, 44(%esp)                  # 4-byte Spill
	je	.LBB11_1
	jmp	.LBB11_2
.LBB11_3:
	movl	40(%esp), %eax                  # 4-byte Reload
	movl	32(%esp), %ecx                  # 4-byte Reload
	movb	$0, (%eax,%ecx)
	addl	$48, %esp
	popl	%esi
	popl	%edi
	popl	%ebx
	retl
.LBB11_4:                               # =>This Inner Loop Header: Depth=1
	movl	12(%esp), %eax                  # 4-byte Reload
	movl	28(%esp), %ecx                  # 4-byte Reload
	movl	40(%esp), %edx                  # 4-byte Reload
	movl	36(%esp), %edi                  # 4-byte Reload
	movl	20(%esp), %esi                  # 4-byte Reload
	movb	(%esi,%eax), %bl
	movl	%eax, %esi
	addl	%edi, %esi
	movb	%bl, (%edx,%esi)
	addl	$1, %eax
	cmpl	%ecx, %eax
	movl	%eax, 12(%esp)                  # 4-byte Spill
	je	.LBB11_3
	jmp	.LBB11_4
.Lfunc_end11:
	.size	string.add, .Lfunc_end11-string.add
                                        # -- End function
	.globl	array.size                      # -- Begin function array.size
	.p2align	4, 0x90
	.type	array.size,@function
array.size:                             # @array.size
.Larray.size$local:
# %bb.0:
	movl	4(%esp), %eax
	movl	-4(%eax), %eax
	retl
.Lfunc_end12:
	.size	array.size, .Lfunc_end12-array.size
                                        # -- End function
	.globl	string.eq                       # -- Begin function string.eq
	.p2align	4, 0x90
	.type	string.eq,@function
string.eq:                              # @string.eq
.Lstring.eq$local:
# %bb.0:
	pushl	%ebx
	subl	$8, %esp
	calll	.L13$pb
.L13$pb:
	popl	%ebx
.Ltmp11:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp11-.L13$pb), %ebx
	movl	20(%esp), %edx
	movl	16(%esp), %ecx
	movl	%esp, %eax
	movl	%edx, 4(%eax)
	movl	%ecx, (%eax)
	calll	strcmp
	cmpl	$0, %eax
	sete	%al
	andb	$1, %al
	movzbl	%al, %eax
	addl	$8, %esp
	popl	%ebx
	retl
.Lfunc_end13:
	.size	string.eq, .Lfunc_end13-string.eq
                                        # -- End function
	.globl	string.neq                      # -- Begin function string.neq
	.p2align	4, 0x90
	.type	string.neq,@function
string.neq:                             # @string.neq
.Lstring.neq$local:
# %bb.0:
	pushl	%ebx
	subl	$8, %esp
	calll	.L14$pb
.L14$pb:
	popl	%ebx
.Ltmp12:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp12-.L14$pb), %ebx
	movl	20(%esp), %edx
	movl	16(%esp), %ecx
	movl	%esp, %eax
	movl	%edx, 4(%eax)
	movl	%ecx, (%eax)
	calll	strcmp
	cmpl	$0, %eax
	setne	%al
	andb	$1, %al
	movzbl	%al, %eax
	addl	$8, %esp
	popl	%ebx
	retl
.Lfunc_end14:
	.size	string.neq, .Lfunc_end14-string.neq
                                        # -- End function
	.globl	string.lt                       # -- Begin function string.lt
	.p2align	4, 0x90
	.type	string.lt,@function
string.lt:                              # @string.lt
.Lstring.lt$local:
# %bb.0:
	pushl	%ebx
	subl	$8, %esp
	calll	.L15$pb
.L15$pb:
	popl	%ebx
.Ltmp13:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp13-.L15$pb), %ebx
	movl	20(%esp), %edx
	movl	16(%esp), %ecx
	movl	%esp, %eax
	movl	%edx, 4(%eax)
	movl	%ecx, (%eax)
	calll	strcmp
	cmpl	$0, %eax
	setl	%al
	andb	$1, %al
	movzbl	%al, %eax
	addl	$8, %esp
	popl	%ebx
	retl
.Lfunc_end15:
	.size	string.lt, .Lfunc_end15-string.lt
                                        # -- End function
	.globl	string.gt                       # -- Begin function string.gt
	.p2align	4, 0x90
	.type	string.gt,@function
string.gt:                              # @string.gt
.Lstring.gt$local:
# %bb.0:
	pushl	%ebx
	subl	$8, %esp
	calll	.L16$pb
.L16$pb:
	popl	%ebx
.Ltmp14:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp14-.L16$pb), %ebx
	movl	20(%esp), %edx
	movl	16(%esp), %ecx
	movl	%esp, %eax
	movl	%edx, 4(%eax)
	movl	%ecx, (%eax)
	calll	strcmp
	cmpl	$0, %eax
	setg	%al
	andb	$1, %al
	movzbl	%al, %eax
	addl	$8, %esp
	popl	%ebx
	retl
.Lfunc_end16:
	.size	string.gt, .Lfunc_end16-string.gt
                                        # -- End function
	.globl	string.le                       # -- Begin function string.le
	.p2align	4, 0x90
	.type	string.le,@function
string.le:                              # @string.le
.Lstring.le$local:
# %bb.0:
	pushl	%ebx
	subl	$8, %esp
	calll	.L17$pb
.L17$pb:
	popl	%ebx
.Ltmp15:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp15-.L17$pb), %ebx
	movl	20(%esp), %edx
	movl	16(%esp), %ecx
	movl	%esp, %eax
	movl	%edx, 4(%eax)
	movl	%ecx, (%eax)
	calll	strcmp
	cmpl	$1, %eax
	setl	%al
	andb	$1, %al
	movzbl	%al, %eax
	addl	$8, %esp
	popl	%ebx
	retl
.Lfunc_end17:
	.size	string.le, .Lfunc_end17-string.le
                                        # -- End function
	.globl	string.ge                       # -- Begin function string.ge
	.p2align	4, 0x90
	.type	string.ge,@function
string.ge:                              # @string.ge
.Lstring.ge$local:
# %bb.0:
	pushl	%ebx
	subl	$8, %esp
	calll	.L18$pb
.L18$pb:
	popl	%ebx
.Ltmp16:
	addl	$_GLOBAL_OFFSET_TABLE_+(.Ltmp16-.L18$pb), %ebx
	movl	20(%esp), %edx
	movl	16(%esp), %ecx
	movl	%esp, %eax
	movl	%edx, 4(%eax)
	movl	%ecx, (%eax)
	calll	strcmp
	cmpl	$-1, %eax
	setg	%al
	andb	$1, %al
	movzbl	%al, %eax
	addl	$8, %esp
	popl	%ebx
	retl
.Lfunc_end18:
	.size	string.ge, .Lfunc_end18-string.ge
                                        # -- End function
	.type	.L.str,@object                  # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s"
	.size	.L.str, 3

	.type	.L.str.1,@object                # @.str.1
.L.str.1:
	.asciz	"%s\n"
	.size	.L.str.1, 4

	.type	.L.str.2,@object                # @.str.2
.L.str.2:
	.asciz	"%d"
	.size	.L.str.2, 3

	.type	.L.str.3,@object                # @.str.3
.L.str.3:
	.asciz	"%d\n"
	.size	.L.str.3, 4

	.ident	"Ubuntu clang version 15.0.7"
	.section	".note.GNU-stack","",@progbits
	.addrsig

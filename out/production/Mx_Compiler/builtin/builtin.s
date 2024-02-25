	.text
	.attribute	4, 16
	.attribute	5, "rv32i2p0_m2p0_a2p0_c2p0"
	.file	"builtin.c"
	.globl	print
	.p2align	1
	.type	print,@function
print:
	mv	a1, a0
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	tail	printf
.Lfunc_end0:
	.size	print, .Lfunc_end0-print

	.globl	println
	.p2align	1
	.type	println,@function
println:
	mv	a1, a0
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	tail	printf
.Lfunc_end1:
	.size	println, .Lfunc_end1-println

	.globl	printInt
	.p2align	1
	.type	printInt,@function
printInt:
	mv	a1, a0
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	tail	printf
.Lfunc_end2:
	.size	printInt, .Lfunc_end2-printInt

	.globl	printlnInt
	.p2align	1
	.type	printlnInt,@function
printlnInt:
	mv	a1, a0
	lui	a0, %hi(.L.str.3)
	addi	a0, a0, %lo(.L.str.3)
	tail	printf
.Lfunc_end3:
	.size	printlnInt, .Lfunc_end3-printlnInt

	.globl	getString
	.p2align	1
	.type	getString,@function
getString:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	li	a0, 256
	call	malloc
	mv	a1, a0
	sw	a1, 8(sp)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end4:
	.size	getString, .Lfunc_end4-getString

	.globl	getInt
	.p2align	1
	.type	getInt,@function
getInt:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	addi	a1, sp, 8
	call	scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end5:
	.size	getInt, .Lfunc_end5-getInt

	.globl	toString
	.p2align	1
	.type	toString,@function
toString:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	sw	a0, 4(sp)
	li	a0, 16
	call	malloc
	lw	a2, 4(sp)
	sw	a0, 8(sp)
	lui	a1, %hi(.L.str.2)
	addi	a1, a1, %lo(.L.str.2)
	call	sprintf
	lw	a0, 8(sp)
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end6:
	.size	toString, .Lfunc_end6-toString

	.globl	string.length
	.p2align	1
	.type	string.length,@function
string.length:
	tail	strlen
.Lfunc_end7:
	.size	string.length, .Lfunc_end7-string.length

	.globl	string.substring
	.p2align	1
	.type	string.substring,@function
string.substring:
	addi	sp, sp, -32
	sw	ra, 28(sp)
	sw	a2, 12(sp)
	sw	a1, 16(sp)
	sw	a0, 4(sp)
	sub	a0, a2, a1
	sw	a0, 8(sp)
	addi	a0, a0, 1
	call	malloc
	lw	a2, 12(sp)
	lw	a1, 16(sp)
	sw	a0, 20(sp)
	mv	a0, a1
	sw	a0, 24(sp)
	blt	a1, a2, .LBB8_2
	j	.LBB8_1
.LBB8_1:
	lw	a0, 20(sp)
	lw	a1, 8(sp)
	add	a2, a0, a1
	li	a1, 0
	sb	a1, 0(a2)
	lw	ra, 28(sp)
	addi	sp, sp, 32
	ret
.LBB8_2:
	lw	a1, 12(sp)
	lw	a0, 24(sp)
	lw	a3, 20(sp)
	lw	a4, 16(sp)
	lw	a2, 4(sp)
	add	a2, a2, a0
	lb	a2, 0(a2)
	sub	a4, a0, a4
	add	a3, a3, a4
	sb	a2, 0(a3)
	addi	a0, a0, 1
	mv	a2, a0
	sw	a2, 24(sp)
	beq	a0, a1, .LBB8_1
	j	.LBB8_2
.Lfunc_end8:
	.size	string.substring, .Lfunc_end8-string.substring

	.globl	string.parseInt
	.p2align	1
	.type	string.parseInt,@function
string.parseInt:
	addi	sp, sp, -48
	sw	ra, 44(sp)
	sw	a0, 20(sp)
	lbu	a1, 0(a0)
	addi	a1, a1, -45
	seqz	a1, a1
	sw	a1, 24(sp)
	call	strlen
	mv	a1, a0
	lw	a0, 24(sp)
	mv	a2, a1
	sw	a2, 28(sp)
	li	a2, 0
	mv	a3, a0
	sw	a3, 32(sp)
	mv	a3, a2
	sw	a3, 36(sp)
	sw	a2, 40(sp)
	bge	a0, a1, .LBB9_3
	j	.LBB9_1
.LBB9_1:
	lw	a0, 20(sp)
	lw	a1, 32(sp)
	lw	a2, 36(sp)
	sw	a2, 8(sp)
	sw	a1, 12(sp)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	sw	a0, 16(sp)
	addi	a0, a0, -48
	andi	a1, a0, 255
	li	a0, 9
	sw	a2, 40(sp)
	bltu	a0, a1, .LBB9_3
	j	.LBB9_2
.LBB9_2:
	lw	a1, 28(sp)
	lw	a0, 12(sp)
	lw	a2, 8(sp)
	lw	a3, 16(sp)
	andi	a3, a3, 255
	li	a4, 10
	mul	a2, a2, a4
	add	a2, a2, a3
	addi	a2, a2, -48
	addi	a0, a0, 1
	mv	a3, a0
	sw	a3, 32(sp)
	mv	a3, a2
	sw	a3, 36(sp)
	sw	a2, 40(sp)
	bne	a0, a1, .LBB9_1
	j	.LBB9_3
.LBB9_3:
	lw	a0, 40(sp)
	lw	ra, 44(sp)
	addi	sp, sp, 48
	ret
.Lfunc_end9:
	.size	string.parseInt, .Lfunc_end9-string.parseInt

	.globl	string.ord
	.p2align	1
	.type	string.ord,@function
string.ord:
	add	a0, a0, a1
	lbu	a0, 0(a0)
	ret
.Lfunc_end10:
	.size	string.ord, .Lfunc_end10-string.ord

	.globl	string.add
	.p2align	1
	.type	string.add,@function
string.add:
	addi	sp, sp, -48
	sw	ra, 44(sp)
	sw	a1, 16(sp)
	sw	a0, 12(sp)
	call	strlen
	mv	a1, a0
	lw	a0, 16(sp)
	sw	a1, 32(sp)
	sw	a1, 20(sp)
	call	strlen
	lw	a1, 32(sp)
	mv	a2, a0
	sw	a2, 24(sp)
	add	a0, a0, a1
	sw	a0, 28(sp)
	addi	a0, a0, 1
	call	malloc
	lw	a1, 32(sp)
	sw	a0, 36(sp)
	li	a0, 0
	mv	a2, a0
	sw	a2, 40(sp)
	blt	a0, a1, .LBB11_2
	j	.LBB11_1
.LBB11_1:
	lw	a1, 24(sp)
	li	a0, 0
	mv	a2, a0
	sw	a2, 8(sp)
	blt	a0, a1, .LBB11_4
	j	.LBB11_3
.LBB11_2:
	lw	a1, 20(sp)
	lw	a0, 40(sp)
	lw	a3, 36(sp)
	lw	a2, 12(sp)
	add	a2, a2, a0
	lb	a2, 0(a2)
	add	a3, a3, a0
	sb	a2, 0(a3)
	addi	a0, a0, 1
	mv	a2, a0
	sw	a2, 40(sp)
	beq	a0, a1, .LBB11_1
	j	.LBB11_2
.LBB11_3:
	lw	a0, 36(sp)
	lw	a1, 28(sp)
	add	a2, a0, a1
	li	a1, 0
	sb	a1, 0(a2)
	lw	ra, 44(sp)
	addi	sp, sp, 48
	ret
.LBB11_4:
	lw	a1, 24(sp)
	lw	a0, 8(sp)
	lw	a3, 36(sp)
	lw	a4, 20(sp)
	lw	a2, 16(sp)
	add	a2, a2, a0
	lb	a2, 0(a2)
	add	a4, a4, a0
	add	a3, a3, a4
	sb	a2, 0(a3)
	addi	a0, a0, 1
	mv	a2, a0
	sw	a2, 8(sp)
	beq	a0, a1, .LBB11_3
	j	.LBB11_4
.Lfunc_end11:
	.size	string.add, .Lfunc_end11-string.add

	.globl	array.size
	.p2align	1
	.type	array.size,@function
array.size:
	lw	a0, -4(a0)
	ret
.Lfunc_end12:
	.size	array.size, .Lfunc_end12-array.size

	.globl	string.eq
	.p2align	1
	.type	string.eq,@function
string.eq:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	seqz	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end13:
	.size	string.eq, .Lfunc_end13-string.eq

	.globl	string.neq
	.p2align	1
	.type	string.neq,@function
string.neq:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	snez	a0, a0
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end14:
	.size	string.neq, .Lfunc_end14-string.neq

	.globl	string.lt
	.p2align	1
	.type	string.lt,@function
string.lt:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end15:
	.size	string.lt, .Lfunc_end15-string.lt

	.globl	string.gt
	.p2align	1
	.type	string.gt,@function
string.gt:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	mv	a1, a0
	li	a0, 0
	slt	a0, a0, a1
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end16:
	.size	string.gt, .Lfunc_end16-string.gt

	.globl	string.le
	.p2align	1
	.type	string.le,@function
string.le:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	slti	a0, a0, 1
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end17:
	.size	string.le, .Lfunc_end17-string.le

	.globl	string.ge
	.p2align	1
	.type	string.ge,@function
string.ge:
	addi	sp, sp, -16
	sw	ra, 12(sp)
	call	strcmp
	not	a0, a0
	srli	a0, a0, 31
	lw	ra, 12(sp)
	addi	sp, sp, 16
	ret
.Lfunc_end18:
	.size	string.ge, .Lfunc_end18-string.ge

	.type	.L.str,@object
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s"
	.size	.L.str, 3

	.type	.L.str.1,@object
.L.str.1:
	.asciz	"%s\n"
	.size	.L.str.1, 4

	.type	.L.str.2,@object
.L.str.2:
	.asciz	"%d"
	.size	.L.str.2, 3

	.type	.L.str.3,@object
.L.str.3:
	.asciz	"%d\n"
	.size	.L.str.3, 4

	.ident	"Ubuntu clang version 15.0.7"
	.section	".note.GNU-stack","",@progbits
	.addrsig

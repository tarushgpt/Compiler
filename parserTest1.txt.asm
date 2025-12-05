# @author Tarush Gupta
# @date 2025-12-04

	.data
	newline: .asciiz "\n"
	
	.text
	.globl __start
__start:
	#block
	#writeln
	#binary operand
	#binary operand
	#number
	li $v0, 6
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 2
	lw $t0 ($sp)
	addu $sp $sp 4
	mul $v0, $v0, $t0
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 3
	lw $t0 ($sp)
	addu $sp $sp 4
	div $t0, $v0
	mflo $v0
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#writeln
	#binary operand
	#binary operand
	#number
	li $v0, 6
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 2
	lw $t0 ($sp)
	addu $sp $sp 4
	div $t0, $v0
	mflo $v0
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 3
	lw $t0 ($sp)
	addu $sp $sp 4
	mul $v0, $v0, $t0
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#writeln
	#binary operand
	#number
	li $v0, 6
	subu $sp $sp 4
	sw $v0 ($sp)
	#binary operand
	#number
	li $v0, 2
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 3
	lw $t0 ($sp)
	addu $sp $sp 4
	mul $v0, $v0, $t0
	lw $t0 ($sp)
	addu $sp $sp 4
	div $t0, $v0
	mflo $v0
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	li $v0, 10
	syscall
	

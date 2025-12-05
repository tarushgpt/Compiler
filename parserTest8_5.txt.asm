# @author Tarush Gupta
# @date 2025-12-04

	.data
	newline: .asciiz "\n"
	varx: .word 0
	vary: .word 0
	varignore: .word 0
	
	.text
	.globl __start
__start:
	#block
	#block
	#assignment: x
	#number
	li $v0, 1
	la $t0 varx
	sw $v0 ($t0)
	#assignment: ignore
	#procedure call: foo
	subu $sp $sp 4
	sw $ra ($sp)
	jal procfoo
	lw $ra ($sp)
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	#writeln
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#writeln
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	li $v0, 10
	syscall
	
	#procedure declaration: foo
procfoo:
	subu $sp $sp 4
	sw $zero ($sp)
	subu $sp $sp 4
	sw $zero ($sp)
	#block
	#assignment: x
	#number
	li $v0, 2
	la $t0 varx
	sw $v0 ($t0)
	#assignment: y
	#number
	li $v0, 7
	sw $v0 0($sp)
	#assignment: ignore
	#procedure call: bar
	subu $sp $sp 4
	sw $ra ($sp)
	jal procbar
	lw $ra ($sp)
	addu $sp $sp 4
	la $t0 varignore
	sw $v0 ($t0)
	#writeln
	#variable: y
	lw $v0 0($sp)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $v0 ($sp)
	addu $sp $sp 4
	jr $ra
	
	#procedure declaration: bar
procbar:
	subu $sp $sp 4
	sw $zero ($sp)
	subu $sp $sp 4
	sw $zero ($sp)
	#block
	#assignment: x
	#number
	li $v0, 3
	la $t0 varx
	sw $v0 ($t0)
	#assignment: y
	#binary operand
	#variable: y
	lw $v0 0($sp)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 2
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	sw $v0 0($sp)
	lw $t0 ($sp)
	addu $sp $sp 4
	lw $v0 ($sp)
	addu $sp $sp 4
	jr $ra
	
